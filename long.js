function Main(input) {
	input = input.split("\n");
	var s = input[0];
	var n = input[1];
	var index = (n - 1)%s.length;
	var output = s[index];
	console.log(output);
}
Main(require("fs").readFileSync("/dev/stdin", "utf8"));

