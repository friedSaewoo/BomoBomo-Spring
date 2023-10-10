function addressFind() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                } console.log(extraAddr);
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById('address_extra').value = extraAddr;

            } else {
                document.getElementById('address_extra').value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('address-num').value = data.zonecode;
            document.getElementById('address').value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('address_detail').focus();
        }
    }).open();
    console.log("성공!");
}

//결제하기 결제하기 클릭시 카카오페이로 이동하는 js

$("#check_module").click(function () {

    const payAmount = parseInt($('#price').val());
    const postCode = $('#address-num').val();
    const date = $('#eventDate').val();

    var IMP = window.IMP; // 생략가능
    IMP.init('imp14503134');
    // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
    // ''안에 띄어쓰기 없이 가맹점 식별코드를 붙여넣어주세요. 안그러면 결제창이 안뜹니다.
    IMP.request_pay({
        pg: 'kakaopay.TC0ONETIME',
        pay_method: 'card',
        merchant_uid: 'merchant_' + new Date().getTime(),
        /*
         *  merchant_uid에 경우
         *  https://docs.iamport.kr/implementation/payment
         *  위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
         */
        name: '이벤트',
        date: date,
        // 결제창에서 보여질 이름
        // name: '주문명 : ${auction.a_title}',
        // 위와같이 model에 담은 정보를 넣어 쓸수도 있습니다.
        amount: payAmount,

        // amount: ${bid.b_bid},
        // 가격
        buyer_name: '안녕',
        // 구매자 이름, 구매자 정보도 model값으로 바꿀 수 있습니다.
        // 구매자 정보에 여러가지도 있으므로, 자세한 내용은 맨 위 링크를 참고해주세요.
        buyer_postcode: postCode,
    }, function (rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '결제 금액 : ' + rsp.paid_amount;
            // success.submit();a
            // 결제 성공 시 정보를 넘겨줘야한다면 body에 form을 만든 뒤 위의 코드를 사용하는 방법이 있습니다.
            // 자세한 설명은 구글링으로 보시는게 좋습니다.
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            // 창이 사라지고 원래 페이지로 이동
        }
        alert(msg);
        window.location.href='/event/list';
    });
});
