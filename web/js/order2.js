

$(function () {
    var radio = $("input[type='radio']")
    console.log(radio.length)
    var radio_msg = 0;
    radio.click(function () {
        radio_msg = this.value
        console.log(radio_msg)
    })

    $('#pay').click(function () {
        if (radio_msg == 0){
            alert("请选择地址")
            return false
        }
        location.href="toPayServlet2?aid=" + radio_msg
    })


    $("#addAddress").click(function () {
        $("#aaaa").css("display","block")
    })
})
