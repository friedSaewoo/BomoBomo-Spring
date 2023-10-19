// *************************주간 회원가입 차트
let dayList=[];
let countList=[];
let sitterTotal=0;
let eventTotal=0;
$(document).ready(function () {
    loadWeeklyRegister();
    loadTotalSales();
    loadNewMatch();
});
function loadWeeklyRegister(){
    $.ajax({
        url: `/admin/rest/weeklyRegister`,
        type: 'get',
        dataType: 'json',
        success: function (result) {
            let joinCanvas = document.getElementById('join-chart');
            joinCanvas.height=235;
            for (let i = 0; i < result.length; i++) {
                let day = result[i].monthDay;
                dayList.push(day);
                let count = result[i].dailyUserCount;
                countList.push(count);
            }
            var data = {
                labels: dayList,
                datasets: [{
                    label: '일별 회원가입',
                    data: countList,
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    borderWidth: 2
                }]
            };

            // 그래프 생성
            var myLineChart = new Chart(joinCanvas.getContext('2d'), {
                type: 'line',
                data: data
            });
        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

function loadTotalSales(){
    $.ajax({
        url: '/admin/rest/sitterTotal',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            let sitterTotal = data.sitterTotal;
            let eventTotal = data.eventTotal;

            console.log('Sitter Total: ' + sitterTotal);
            console.log('Event Total: ' + eventTotal);

            var data = {
                labels: ['시터 매칭', '이벤트'],
                datasets: [{
                    data: [sitterTotal, eventTotal], // 각 섹션의 비율
                    backgroundColor: ['rgba(75, 192, 192, 0.5)', 'rgba(255, 99, 132, 0.5)'], // 섹션의 배경색
                }]
            };


            var options = {
                responsive: true,
                maintainAspectRatio: false,
            };

            // 파이 차트 그리기
            var ctx = document.getElementById('sales').getContext('2d');
            var myPieChart = new Chart(ctx, {
                type: 'pie',
                data: data,
                options: options
            });


            var exit = document.querySelector('.main-header-exit');

            exit.addEventListener('click', function() {
                window.location.href = '/admin/logout';
            });
        },
        error: function (error) {
            console.error('오류 발생: ' + error);
        }
    });
}
function loadNewMatch() {
    $.ajax({
        url: `/admin/rest/newMatch`,
        type: 'get',
        dataType: 'json',
        success: function (result) {
            console.log(result);
            createNewMatch(result);
        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}
function createNewMatch(result){
    if(result!=0){
        let main =$('.bottom-main');
        main.empty();
        $.each(result, function(index,match){
           main.append(
           '<div class="wait-item">'+
               '<div class="match-status">'
                   +'<p>매칭번호 :'+'<span>'+match.matchNumber+'</span>'+'</p>'
                   +'<p>' + (match.status == 0 ? '면접대기' : (match.status == 1 ? '결제대기' : (match.status == 2 ? '결제완료' : 'X'))) + '</p>'
               +'</div>' +
               '<div class="user-info">'+
                   '<p>회원 번호 : <span>'+ match.userNumber +'</span></p>'+
                   '<p>회원 이름 : <span>'+ match.userName +'</span></p>'+
               '</div>'+
               '<div class="emp-info">'+
                   '<p>직원 번호 : <span>'+ match.empNumber +'</span></p>'+
                   '<p>직원 이름 : <span>'+ match.empName +'</span></p>'+
               '</div>'+
               '<div class="manage-btn-container">'+
                   '<a href="/admin/match/detail?matchNumber='+match.matchNumber+'"><button>관리</button></a>'+
               '</div>'+
           '</div>'
           );
        });
    }
}