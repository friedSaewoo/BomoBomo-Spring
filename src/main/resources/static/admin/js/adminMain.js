// *************************주간 회원가입 차트
let dayList=[];
let countList=[];
$(document).ready(function () {
    loadWeeklyRegister();
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


// console.log(dayList);
// console.log(countList);
// var signupEx = [5, 8, 12, 15, 50, 18, 14];

// // 레이블 생성
// var labels = [];
// for (var i = 1; i <=7; i++) {
//     labels.push("09-" + (i < 10 ? '0' + i : i));
// }





// ****************************주간 매출량 차트

// var salesCanvas = document.getElementById('sales');
// salesCanvas.height=220;
// // 이벤트, 시터찾기 각 매출
// var eventSales = [300, 400, 200, 500, 600, 550, 350];
// var sitterSales = [700, 800, 600, 1000, 1400, 1250, 950];
// // 매출 총합
// var salesData = [1000, 1200, 800, 1500, 2000, 1800, 1300];
// // 그래프 데이터 설정
// var data = {
//     labels: labels,
//     datasets: [
//         {
//             label: '이벤트',
//             data: eventSales,
//             backgroundColor: 'rgba(75, 192, 192, 0.5)',
//             borderColor: 'rgba(75, 192, 192, 1)',
//             borderWidth: 1
//         },
//         {
//             label: '시터 찾기',
//             data: sitterSales,
//             backgroundColor: 'rgba(255, 99, 132, 0.5)',
//             borderColor: 'rgba(255, 99, 132, 1)',
//             borderWidth: 1
//         }
//     ]
// };
//
//
// var myBarChart = new Chart(salesCanvas, {
//     type: 'bar',
//     data: data,
//     options: {
//         scales: {
//             y: {
//                 beginAtZero: true
//             }
//         }
//     }
// });
var data = {
    labels: ['시터 매칭', '이벤트'],
    datasets: [{
        data: [70, 30], // 각 섹션의 비율
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
