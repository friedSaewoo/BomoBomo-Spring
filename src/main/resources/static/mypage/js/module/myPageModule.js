
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


