// 페이징 처리

// 검색어 저장
let searchKeyword='';

// 첫 화면
$(document).ready(function () {
    loadPage(1,getSearchVo());
});

// 페이지 숫자 클릭
$(document).on('click', '.bt', function (e) {
    e.preventDefault();
    const page = $(this).data('num');
    $('.keyword').val('');
    loadPage(page, getSearchVo());
});

// 검색 버튼 클릭
$('.submit').on('click', function (){
    searchKeyword = $('.keyword').val();
    loadPage(1,getSearchVo());
})

// input 데이터
function getSearchVo(){
    let cate = $('.cate').val();
    let keyword = $('.keyword').val();
    return {
        cate : cate,
        keyword : searchKeyword
    };
}
// ajax
function loadPage(page, searchVo) {
    $.ajax({
        url: `/admin/rest/match/list/${page}`,
        type: 'get',
        data : searchVo,
        dataType: 'json',
        success: function (result) {
            console.log(result.pageVo);
            console.log(result.matchList);

            loadEmpList(result);

        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기서부터 작업
// ajax로 가져온 데이터를 사용해 페이징처리
function loadEmpList(result){

    if(result.adminEmpList!=0){
        let empList = $('.emp-list');
        empList.empty();

        $.each(result.adminEmpList, function (index, emp) {
            //
            // <div class="post">
            //     <div class="emp-num">001</div>
            //     <div class="emp-name">김성찬</div>
            //     <div class="date">2023-09-13</div>
            //     <div class="emp-phone">01071921375</div>
            //     <div class="emp-email">zriag@naver.com</div>
            // </div>

            let empDiv = $('<div class="post">');
            empDiv.append('<div class="emp-num">' + emp.empNumber + '</div>');
            empDiv.append('<div class="emp-name">' + emp.empName + '</div>');
            empDiv.append('<div class="date">' + emp.empDate + '</div>');
            empDiv.append('<div class="emp-phone">' + emp.empPhone + '</div>');
            empDiv.append('<div class="emp-email">'+ emp.empEmail + '</div>');
            empList.append(empDiv);

        });
    }else{
        let empList = $('.emp-list');
        empList.empty(); // 기존 데이터 지우기
    }
    pagination(result.pageVo);
}
// 각 페이징 버튼 처리
function pagination(pageVo) {
    let $pagenation = $('.pagination-container');
    $pagenation.empty();

    if (pageVo.prev) {
        $pagenation.append(`
                    <a href="#" data-num="${pageVo.startPage-1}" class="bt prev">&lt;</a>
            `);
    }
    if(pageVo.realEnd!=0){
        for (let page = pageVo.startPage; page <= pageVo.endPage; page++) {
            if(page == pageVo.criteria.page){
                $pagenation.append(`
                    <a href="#" class="bt num on" data-num="${page}">${page}</a>
                `
                );

            }else {
                $pagenation.append(`
                    <a href="#" class="bt num" data-num="${page}">${page}</a>
                `
                );
            }

        }
    }else{

    }
    if (pageVo.next) {
        $pagenation.append(`
                <a href="#" data-num="${pageVo.endPage+1}"  class="bt next">&gt;</a>
            `);
    }
}


// // 인풋박스에 엔터키를 누르면 search버튼 클릭 처리
// function inputEnter(event) {
//     if (event.key === "Enter") {
//         document.getElementById("submit").click();
//     }
// }
// document.getElementById("search").addEventListener("keyup", inputEnter);

