/**
 * 往画布中填充随机验证码，并将验证码返回到input，由后台验证
 * @param canvas_id  画布id
 * @param input_id  隐藏域id
 */
function verifycode(canvas_id,input_id) {
    var canvas = document.getElementById(canvas_id);
    var context = canvas.getContext("2d");
    var canvas_x = canvas.width;
    var canvas_y = canvas.height;
    context.fillStyle = "gray";
    context.fillRect(0, 0, canvas_x, canvas_y);
    context.fill();

    //生成4位随机数
    var chars = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
    var verification_code = "";
    for (var i = 0; i < 4; i++) {
        var num = Math.ceil(Math.random() * 51);
        verification_code += chars[num];
    }

    //填充随机数
    context.font = "italic 25px arial";
    context.strokeStyle = "#f00";
    context.lineWidth = 2;
    context.strokeText(verification_code.toString(), 10, canvas_y * 4 / 5);

    //绘制干扰点
    context.fillStyle = "#00f";
    for (var i = 0; i < 100; i++) {
        context.beginPath();
        var x = Math.ceil(Math.random() * canvas_x) - 2;
        var y = Math.ceil(Math.random() * canvas_y) - 2;
        context.arc(x, y, 1, 0, 2 * Math.PI);
        //context.fillRect(x, y, 2, 2);
        context.fill();
    }

    document.getElementById(input_id).value= verification_code.toString();
}

