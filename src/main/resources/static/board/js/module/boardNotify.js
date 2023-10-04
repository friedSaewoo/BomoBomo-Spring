


//json으로 데이터를 받아온다.
export function loadPage(page, searchVo) {
    $.ajax({
        url: `/notices/list/${page}`,
        type: 'get',
        data : searchVo,
        dataType: 'json',
        success: function (result) {
            console.log(result.pageVo);
            console.log(result.boardNoticeList);

            //받아온 데이터를 noticeList함수에 넣어주어 화면에 뿌려준다.
            noticeList(result);


        },
        error: function (a, b, c) {
            console.error(c);
        }
    });



}


export function noticeList(result) {
    let text = '';


    if(result.boardNoticeList.length != 0){
        result.boardNoticeList.forEach(r => {

            text += `
                <tr>
                    <td>${r.noticeNumber}</td>
                    <td><a href="/board/detail?noticeNumber=${r.noticeNumber}"><strong>${r.noticeTitle}</strong></a></td>
                    <td>관리자</td>
                    <td>${r.noticeRegisterDate}</td>
                    <td>${r.noticeCount}</td>
                </tr>
            `;
        });

    }else {
        text = `
        
            <tr class="non-notice-search-result">
                <td > 검색결과가 없습니다.</td>
               
            </tr>
            <tr class="non-notice-search-result backBtn">
                <td colspan="3" ><a href="/board/notice">목록으로 돌아가기</a></td >
               <td></td>
               <td></td>
               <td></td>
               <td></td>
            </tr>
        `;
    }

    $('.content').html(text);
    //동시에 페이징처리
    updatePagination(result.pageVo);
}


//페이징처리
function updatePagination(pageVo) {
    let $pagenation = $('.notice-pagenation-container ul');
    $pagenation.empty();

    if (pageVo.prev) {
        $pagenation.append(`
                <li class="page-num"><a href="#" data-num="${pageVo.startPage-1}">&lt;</a></li>
            `);
    }

    //게시물이 1개도 존재하지 않는다면 페이징 표시 x
    //한 개라도 존재할 때 페이징 번호가 나타난다.
    if(pageVo.realEnd!=0){
        for (let page = pageVo.startPage; page <= pageVo.endPage; page++) {
            if(page == pageVo.criteria.page){
                $pagenation.append(`
                    <li class="page-num active-page"><a href="#" class="on" data-num="${page}">${page}</a></li>
                `
                );

            }else {
                $pagenation.append(`
                    <li class="page-num "><a href="#" class="on" data-num="${page}">${page}</a></li>
                `
                );
            }

        }
    }else{
        `<li></li>`
    }




    if (pageVo.next) {
        $pagenation.append(`
            <li class="page-num"> <a href="#" data-num="${pageVo.endPage+1}">&gt;</a></li>
            `);
    }
}