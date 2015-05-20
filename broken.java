import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
 
public class Main{
    
    public int[]available;
    public String target;
    public int limited;
    
    public Main(String target,int limited){
        this.available=new int[10];
        this.target=target;
        this.limited=limited;
    }
    private int getIntByIndex(int index){
        return this.target.charAt(index)-'0';
    }
    
    private BigInteger substract(String str1,String str2){
        BigInteger a=new BigInteger(str1);
        BigInteger b=new BigInteger(str2);
        if(a.compareTo(b)>0){
            return a.subtract(b);
        }else{
            return b.subtract(a);
        }
    }
    private String convertTo(int[]array){
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<array.length;i++){
            buffer.append(array[i]);
        }
        return buffer.toString();
    }
    
    public void solve(){
        int index=0;
        int k=this.limited;
        for(;index<this.target.length();index++){            
            int j=this.getIntByIndex(index);
            if(this.available[j]==0){
                if(k==0){
                    break;
                }
                this.available[j]=1;
                k--;
            }            
        }
        if(index==this.target.length()){
            System.out.println("0");
            return;
        }
        int[]copy=Arrays.copyOf(this.available, this.available.length);
        BigInteger diff1=this.substract(this.convertTo(this.calculateMax(copy, index)), this.target);
        BigInteger diff2=this.substract(this.target,this.convertTo(this.calculateMin(this.available, index)));
        if(diff1.compareTo(diff2)>0){
            System.out.println(diff2.toString());
        }else{
            System.out.println(diff1.toString());
        }
    }
    
    private int getValue(int[] avail,boolean less){
        if(less){
            for(int i=0;i<=9;i++){
                if(avail[i]>0)
                    return i;
            }
        }else{
            for(int i=9;i>=0;i--){
                if(avail[i]>0)
                    return i;
            }
        }
        return -1;
    }
    
    public int[] calculateMax(int[] avail,int index){
        int[]result=new int[this.target.length()];
        int nextValue=Integer.MIN_VALUE;
        for(int i=0;i<index;i++){
            result[i]=this.getIntByIndex(i);
        }
        for(int i=0;i<=9;i++){
            if(avail[i]>0&&i>this.getIntByIndex(index)){
                nextValue=i;
                break;
            }
        }
        if(nextValue==Integer.MIN_VALUE){
            nextValue=this.getIntByIndex(index-1)+1;
            for(int j=index-1;j>=0;j--){
                if(result[j]==nextValue-1){
                    result[j]=nextValue;
                }
            }
            result[index-1]=nextValue;
            avail[nextValue-1]=0;
            int min=Integer.MAX_VALUE;
            if(avail[nextValue]==0){
                avail[nextValue]=1;
                min=this.getValue(avail, true);
            }
            for(int j=index;j<this.target.length();j++){
                result[j]=min;
            }
        }else{
            result[index] = nextValue;
            int min=this.getValue(avail, true);
            for(int i = index + 1; i < result.length; i++)
            {
                result[i] = min;
            }
        }
        return result;
    }
    
    public int[] calculateMin(int[] avail,int index){
        int[]result=new int[this.target.length()];
        int nextValue=Integer.MIN_VALUE;
        for(int i=0;i<index;i++){
            result[i]=this.getIntByIndex(i);
        }
        for(int i=9;i>=0;i--){
            if(avail[i]>0&&i<this.getIntByIndex(index)){
                nextValue=i;
                break;
            }
        }
        if(nextValue==Integer.MIN_VALUE){
            if(index==1&&this.getIntByIndex(0)==1){
                result=new int[this.target.length()-1];
                for(int j=0;j<result.length;j++){
                    result[j]=9;
                }
            }else{
                nextValue=this.getIntByIndex(index-1)-1;
                for(int j=index-1;j>=0;j--){
                    if(result[j]==nextValue+1){
                        result[j]=nextValue;
                    }
                }
                result[index-1]=nextValue;
                avail[nextValue+1]=0;
                int max=Integer.MIN_VALUE;
                if(avail[nextValue]==0){
                    avail[nextValue]=1;
                    max=this.getValue(avail, false);
                }
                for(int j=index;j<this.target.length();j++){
                    result[j]=max;
                }
            }
        }else{
            result[index] = nextValue;
            int max=this.getValue(avail, false);
            for(int i = index + 1; i < result.length; i++)
            {
                result[i] = max;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Main m=new Main(scanner.next(),scanner.nextInt());
        m.solve();
    }
}