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
                currentStatus.text('결제완료');
            }
        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

function loadStatus(status){
    // let empDiv = $('<div class="post">');
    // empDiv.append('<div class="emp-num">' + emp.empNumber + '</div>');
    // empDiv.append('<div class="emp-name">' + emp.empName + '</div>');
    // empDiv.append('<div class="date">' + emp.empDate + '</div>');
    // empDiv.append('<div class="emp-phone">' + emp.empPhone + '</div>');
    // empDiv.append('<div class="emp-email">'+ emp.empEmail + '</div>');
    // empList.append(empDiv);
}