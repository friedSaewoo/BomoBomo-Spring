
export function getSitterReviewList (page, callback){
    $.ajax({
        url : `/myPages/sitterReviewList`,
        type : 'get',
        data : {page : page},
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : function (a,b,c){
            console.error(c);
        }
    });
}

export function getEventReviewList (pages, callback){
    $.ajax({
        url : `/myPages/eventReviewList`,
        type : 'get',
        data : {pages : pages},
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : function (a,b,c){
            console.error(c);
        }
    });
}



//결제페이지에 결제 정보 넣기
export function buyInfo(callback){
    $.ajax({

        url:`/myPages/purchase`,
        type:'post',

        dataType: 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        }, error : function (a,b,c){
            console.error(c);
        }

    })
}


