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
            loadMatchList(result);

        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기서부터 작업
// ajax로 가져온 데이터를 사용해 페이징처리
function loadMatchList(result){
    if(result.matchList!=0){
        let matchContainer = $('.match-container');
        matchContainer.empty();

        $.each(result.matchList, function (index, match) {
            let matchDiv = $('<div class="post">');
            let matchNumber = match.matchNumber;
            matchDiv.append('<div class="status">'+
                '<p>매칭 번호 : <span>' + match.matchNumber + '</span></p>'+
                '<p>' + (match.status == 0 ? '면접대기' : (match.status == 1 ? '결제대기' : (match.status == 2 ? '결제완료' : 'X'))) + '</p>' +
                '</div>');
            matchDiv.append('<div class ="user">' +
                '<p>회원 번호 : <span>'+ match.userNumber +'</span></p>'+
                '<p>회원 이름 : <span>'+ match.userName +'</span></p>'+
                '<p>회원 아이디 : <span>'+ match.userId +'</span></p>'+
                '<p>연락처  : <span>'+ match.userPhone +'</span></p>'+
                '</div>');
            matchDiv.append('<div class = "emp" >' +
                '<p>직원 번호 : <span>'+ match.empNumber +'</span></p>'+
                '<p>직원 이름 : <span>'+ match.empName +'</span></p>'+
                '<p>연락처 : <span>'+ match.empPhone +'</span></p>'+
                '<p>이메일 : <span>'+ match.empEmail +'</span></p>'+
                '</div>');
            matchDiv.append('<div class = "box">'+
            '<a href="/admin/match/detail?matchNumber='+matchNumber+'"><button>' + '관리' +'</button></a>'+
            '</div>');
            matchContainer.append(matchDiv);
        });
    }else{
        let matchContainer = $('.match-container');
        matchContainer.empty();
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


// 인풋박스에 엔터키를 누르면 search버튼 클릭 처리
function inputEnter(event) {
    if (event.key === "Enter") {
        document.getElementById("submit").click();
    }
}
document.getElementById("search").addEventListener("keyup", inputEnter);

