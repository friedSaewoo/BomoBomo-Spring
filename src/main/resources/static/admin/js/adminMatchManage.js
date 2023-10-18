let button = document.querySelector('.update-status');
button.addEventListener('click', function () {
    let matchNumber = document.getElementById('matchNumber').value;
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