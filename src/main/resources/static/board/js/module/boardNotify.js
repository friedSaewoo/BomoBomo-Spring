//json으로 데이터를 받아온다.
export function loadPage(page, searchVo, callback) {
    $.ajax({
        url: `/notices/list/${page}`,
        type: 'get',
        data : searchVo,
        dataType: 'json',
        success: function (result) {
            console.log(result.pageVo);
            console.log(result.boardNoticeList);

            //받아온 데이터를 noticeList함수에 넣어주어 화면에 뿌려준다.
            // noticeList(result);

            if(callback){
                callback(result);
            }

        },
        error: function (a, b, c) {
            console.error(c);
        }
    });

}

