function Main(input) {
	input = input.split("\n");
	var s = input[0].split(" ");
	var output = getyear(s[1]) - getyear(s[0]-1);
	console.log(output);
}

function get4year(num) {
	return parseInt(num/4);
}
function get100year(num) {
	return parseInt(num/100);
}
function get400year(num) {
	return parseInt(num/400);
}

function getyear(num) {
    return get4year(num) - get100year(num) + get400year(num);
}

//Main(require("fs").readFileSync("data.txt", "utf8"));
Main(require("fs").readFileSync("/dev/stdin", "utf8"));



//计算闰年