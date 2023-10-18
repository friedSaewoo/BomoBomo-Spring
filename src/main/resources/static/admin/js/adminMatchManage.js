let button = document.querySelector('.update-status');
let matchNumber = document.getElementById('matchNumber').value;
button.addEventListener('click', function () {
    let status = document.getElementById('status').value;
    updateStatus(matchNumber, status);
});
function updateStatus(matchNumber, status) {
    $.ajax({
        url: `/admin/rest/match/status`,
        type: 'get',
        data:{
            matchNumber : matchNumber,
            status : status
        },
        dataType: 'json',
        success: function (result) {
            console.log(result);
            let currentStatus = $('.current-status');
            currentStatus.empty();
            if(result == '0'){
                currentStatus.text('면접대기');
            }else if (result == '1'){
                currentStatus.text('결제대기');
            }
        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

$('.submit-order').on('click',  function (){
    purchasePage();
});
function purchasePage(){
    let modal = document.getElementById("modal");
    modal.style.display = "block";
    // genderSecond 요소를 가져옴
    let genderSecondElement = document.getElementById("genderSecond");
// genderSecond 요소의 값 가져오기
    let genderSecondValue = genderSecondElement.value;

    if (genderSecondValue == 'n') {
        let textArea1 = document.getElementById("text-area-1");
        textArea1.style.display = "none";
    }
}

window.onclick = function(event) {
    let modal = document.getElementById("modal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

$(document).ready(function() {
    // totalPrice 초기화
    let totalPrice = 0;

    // "추가" 버튼 클릭 이벤트 핸들러
    $("#addEst").click(function() {
        // 입력된 데이터 가져오기
        let estTitle = $("#est-title").val();
        let estContent = $("#est-content").val();
        let estPrice = parseInt($("#est-price").val());

        // 입력값이 비어 있는지 확인
        if (estTitle.trim() === "") {
            alert("title을 입력하세요");
            return;
        }
        if (estContent.trim() === "") {
            alert("content를 입력하세요");
            return;
        }
        if (isNaN(estPrice)) { // estPrice가 숫자가 아닌 경우
            alert("price를 입력하세요");
            return;
        }

        // totalPrice 업데이트
        totalPrice += estPrice;

        // totalMoney 업데이트
        $(".totalMoney").text(totalPrice + "원");

        // product-info에 데이터 추가
        $(".product-info").append(
            "<li class='product-detail'>" +
            "<p class='product-title'>" + "[보모보모] " +"<span class='estTitle'>"+ estTitle + "</span>"+"</p>" +
            "<p class='usetime'>" + "활동 : "+"<span class='estContent'>" + estContent + "</span>"+"</p>" +
            "<p class='pay'><span>결제금액</span>" + "<span class='pay-price'>" + estPrice + "</span>" + "원" + "</p>" +
            "<button class='remove-item'>X</button>" +
            "</li>");

        // 입력 창 비우기
        $("#est-title").val("");
        $("#est-content").val("");
        $("#est-price").val("");
    });
    // X 버튼 클릭 이벤트 핸들러
    $(".product-info").on("click", ".remove-item", function() {
        let price = $(this).closest("li").val("pay");
        console.log(price);
        $(this).closest("li").remove();
    });
});
$("#submitEst").on("click",function (){
    let length = $('.product-detail').length;
    let ar = [];

    for(let i=0; i<length; i++){
        let $detail = $('.product-detail');
        let title = $($detail[i]).find('.estTitle').text();
        let useTime = $($detail[i]).find('.estContent').text();
        let pay = $($detail[i]).find('.pay-price').text();

        let obj = {
            estTitle : title,
            estContent : useTime,
            estPrice : pay,
            matchNumber : matchNumber
        };

        ar.push(obj);
    }

   $.ajax({
        url: `/admin/rest/est`,
        type:'post',
        data: JSON.stringify(ar),
       contentType : 'application/json; charset=utf-8',
       success:function (result){

       },
       error: function (a,b,c){
            console.log("실패");
            console.error(c);
       }
   });
});