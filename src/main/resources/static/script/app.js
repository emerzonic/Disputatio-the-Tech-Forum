
//==========================================================
//GENERAL PAGE OPERATIONS
//==========================================================
//add auto height to input fields
autosize(document).getElementsByClassName("autosize-input");


//control navbar background color on page scroll
$(function() {
    $(document).scroll(function() {
        var $nav = $("#mainNav");
        if ($(window).scrollTop() > $nav.height()) {
            $nav.addClass("bg-dark").removeClass("mt-5");

        } else {
            $nav.removeClass("bg-dark").addClass("mt-5");
        }
    })
});


//Display cancel and submit buttons when comment input is focused
$( "#commentInput" ).focus(function() {
    $("#commentCancelButton, #commentSubmitButton").removeClass("d-none");
});


//clear the comment input when comment is canceled
$("#commentCancelButton").click(function() {
	$("#commentInput").val("");
});


//Display reply form when reply button is clicked
$( ".reply-action-button" ).click(function(event) {
    var id = event.target.getAttribute("data-id");
    $("#replyForm"+id).removeClass("d-none");
});

//clear the reply input when reply is canceled
$(".reply-cancel-button").click(function(event) {
    var id = event.target.getAttribute("data-id");
    $("#replyInput"+id).val("");
});



//=====================================================
//COMMENT AJAX OPERATIONS
//=====================================================
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


function reloadPageLocation(sec){
    setTimeout(function(){
        location.reload();
    }, sec);
}

//submitting new comment
$("#commentForm").submit(function(event) {
    event.preventDefault();
    // retrieve data from form
    var $commentInput = $("#commentInput");
    var text = $commentInput.val();
    var  postId = $("#postId").val();

    // AJAX post the data to the comment controller.
    var url = "/comment/add";
    var data = {text:text, postId:postId};
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    }).done(function( msg ) {
        console.log( msg );
    });
    $commentInput.val('');
    reloadPageLocation(2000);
});


//editing comment
$(document).on("click", ".commentEdit", function(event) {
    var id = event.target.getAttribute("commentId");
    $.ajax({
        method: "GET",
        url: "/comment/edit/"+id
    })
        .done(function( comment ) {
            if(comment){
              var $commentDiv = $("#comment-media"+comment.id);
                var $commentTextarea = $("#comment-textarea"+comment.id);
                var $commentFormDiv = $("#comment-form"+comment.id);
              $commentDiv.toggleClass("d-none");
              $commentTextarea.val(comment.text);
              $commentFormDiv.toggleClass("d-none");
            }
        });
});



//cancel edit
$(document).on("click", ".cancel-edit", function(event) {
    var id = event.target.getAttribute("commentId");
    var $commentDiv = $("#comment-media"+id);
    var $commentFormDiv = $("#comment-form"+id);
    var $commentTextarea = $("#comment-textarea"+id);
    $commentDiv.toggleClass("d-none");
    $commentFormDiv.toggleClass("d-none");
    $commentTextarea.val("");
});


//submitting edited comment
$(document).on("click", ".submit-comment", function(event) {
    event.preventDefault();
    var id = event.target.getAttribute("commentId");
    var $commentTextarea = $("#comment-textarea"+id);
    var text = $commentTextarea.val();

    // AJAX post the updated comment to the comment controller.
    var url = "/comment/update";
    var data = { id:id,text:text};
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    }).done(function( msg ) {
        console.log( msg );
    });
    $commentTextarea.val('');
    reloadPageLocation(2000);
});



//deleting comment
$(document).on("click", ".commentDelete", function(event) {
    var id = event.target.getAttribute("commentId");
    $.ajax({
        method: "GET",
        url: "/comment/delete/"+id,
    })
        .done(function( msg) {
        });
    reloadPageLocation(2000);
});



//=====================================================
//REPLY AJAX OPERATIONS
//=====================================================

//submitting new reply
$(document).on("click", ".reply-submit-button", function(event) {
    event.preventDefault();
    var id = event.target.getAttribute("data-id");
    // retrieve data from reply input
    var $replyInput = $("#replyInput"+id);
    var text = $replyInput.val();
    var data = {text:text, commentId:id};

    // AJAX post the data to the reply controller.
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': "/reply/add",
        'data': JSON.stringify(data),
        'dataType': 'json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    }).done(function( msg ) {
        console.log( msg );
    });
    $replyInput.val('');
    reloadPageLocation(2000);
});


//editing reply
$(document).on("click", ".replyEdit", function(event) {
    var id = event.target.getAttribute("replyId");
    $.ajax({
        method: "GET",
        url: "/reply/edit/"+id
    })
        .done(function( reply ) {
            if(reply){
                var $replyDiv = $("#reply-media"+reply.id);
                var $replyTextarea = $("#reply-textarea"+reply.id);
                var $replyFormDiv = $("#reply-form"+reply.id);
                $replyDiv.toggleClass("d-none");
                $replyTextarea.val(reply.text);
                $replyFormDiv.toggleClass("d-none");
            }
        });
});



//cancel edit
$(document).on("click", ".cancel-edit", function(event) {
    var id = event.target.getAttribute("replyId");
    var $replyDiv = $("#reply-media"+id);
    var $replyFormDiv = $("#reply-form"+id);
    var $replyTextarea = $("#reply-textarea"+id);
    $replyDiv.toggleClass("d-none");
    $replyFormDiv.toggleClass("d-none");
    $replyTextarea.val("");
});


//submitting edited reply
$(document).on("click", ".submit-reply", function(event) {
    event.preventDefault();
    var id = event.target.getAttribute("replyId");
    var $replyTextarea = $("#reply-textarea"+id);
    var text =  $replyTextarea.val();

    // AJAX post the updated reply to the reply controller.
    var url = "/reply/update";
    var data = { id:id,text:text};
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    }).done(function( msg ) {
        console.log( msg );
    });
    $replyTextarea.val('');
    reloadPageLocation(2000);
});



//deleting reply
$(document).on("click", ".replyDelete", function(event) {
    var id = event.target.getAttribute("replyId");
    $.ajax({
        method: "GET",
        url: "/reply/delete/"+id
    })
        .done(function( msg ) {
            console.log( msg );
        });
    reloadPageLocation(2000);
});



//=====================================================
//Like AJAX toggle
//=====================================================
$(document).on("click", ".toggle-like", function(event) {
    var author = event.target.getAttribute("data-author"),
        entityType = event.target.getAttribute("data-entity"),
        postId = event.target.getAttribute("postId"),
        commentId = event.target.getAttribute("commentId"),
        replyId = event.target.getAttribute("replyId"),
        data;

    if(entityType === "post"){
        data = {author: author, postId:postId, commentId:commentId, replyId:replyId};
        postLike(data)
    }else if(entityType === "comment"){
        data = {author: author, postId:postId, commentId:commentId, replyId:replyId};
        postLike(data)
    }else{
        data = {author: author, postId:postId, commentId:commentId, replyId:replyId};
        postLike(data)
    }

    // // AJAX post the data to the like controller.
    function postLike(data){
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': "/like/toggle-like",
        'data': JSON.stringify(data),
        'dataType': 'json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    }).done(function( msg ) {
        console.log( msg );
    });
        reloadPageLocation(2000);
    }
});



