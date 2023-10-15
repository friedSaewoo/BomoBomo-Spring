//돌봄 서비스 리뷰 불러오기(검색포함)
export function getServiceReviewList(page, searchReviewVo, callback){

    $.ajax({

        url:`/reviews/service/${page}`,
        type:'get',
        data: searchReviewVo,
        dataType:'json',
        success :function (result){
            console.log(result.pageReviewVo);
            console.log(result.serviceReviewList);


            if(callback){
                callback(result);
            }

        }, error :
            function (a,b,c){
                console.error(c);
            }

    });
}



//이벤트 서비스 리뷰 불러오기(검색포함)
export function getEventReviewPage(page, searchReviewVo, callback){
    $.ajax({

        url:`/reviews/eventReview/${page}`,
        type:'get',
        data:searchReviewVo,
        dataType: 'json',
        success : function (result){
            console.log(result.eventReviewList);
            console.log(result.pageEventReviewVo)

            if(callback){
                callback(result)
            }

        },error : function (a,b,c){
            console.error(c)
        }


    })
}






