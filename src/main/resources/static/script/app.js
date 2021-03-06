
//==========================================================
//GENERAL PAGE OPERATIONS
//==========================================================
//add auto height to input fields
autosize(document.getElementsByClassName("autosize-input"));



//control navbar background color on page scroll
$(function() {
    $(document).scroll(function() {
        var $nav = $("#mainNav");
        if ($(window).scrollTop() > $nav.height()) {
            $nav.addClass("bg-dark").removeClass("mt-lg-5","mt-xl-5","mt-md-5");
        } else {
            $nav.removeClass("bg-dark").addClass("mt-lg-5","mt-xl-5","mt-md-5");
        }
    })
});


//change the navbar background on sm when it is toggled
$( ".navbar-toggler" ).click(function() {
    $("#mainNav").addClass("bg-dark");
});


//Display cancel and submit buttons when comment input is focused
$( "#commentInput" ).focus(function() {
    $("#commentCancelButton, #commentSubmitButton").removeClass("d-none");
    $("#newCommentError, #editCommentError").text("");
});


//Remove error text when comment input is focused
$( "#commentEdittInput" ).focus(function() {
    $("#editCommentError").text("");
});


//clear the comment input when comment is canceled
$("#commentCancelButton").click(function() {
	$("#commentInput").val("");
	$("#commentCancelButton, #commentSubmitButton").toggleClass("d-none");
});


//Display reply form when reply button is clicked
$( ".reply-action-button" ).click(function(event) {
    var id = event.target.getAttribute("data-id");
    $("#editReplyErrorError"+id).text("");
    $("#replyForm"+id).removeClass("d-none");
});

//clear the reply input when reply is canceled
$(".reply-cancel-button").click(function(event) {
    var id = event.target.getAttribute("data-id");
    $("#editReplyErrorError"+id).text("");
    $("#replyForm"+id).toggleClass("d-none");
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

function _makeAjaxPostCall(data, url, requestType, $inputField){
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': requestType,
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        }
    }).done(function( msg ) {
        console.log( msg );
    });
    if ($inputField!== null){
        $inputField.val('');
    }
    reloadPageLocation(2000);
}

//=====================================================
//COMMENT AJAX OPERATIONS
//=====================================================

//submitting new comment
$("#commentForm").submit(function(event) {
    event.preventDefault();
    // retrieve data from form
    var $commentInput = $("#commentInput");
    var text = $commentInput.val().trim();
    //If field empty stop and show error message
    if(text === ""){
        return errorField.text("Comment field cannot be empty!");
    }
    var postId = $("#postId").val();
    var errorField = $("#newCommentError");
    var url = "/comment/add";
    var data = {text:text, postId:postId};
    var requestType = "POST";

    console.log(data);


    // AJAX post the data to the comment controller.
    _makeAjaxPostCall(data,url,requestType,$commentInput);
});




//editing comment
$(document).on("click", ".commentEdit", function(event) {
    var id = event.target.getAttribute("commentId");
    $.ajax({
        method: "GET",
        url: "/comment/edit/"+id,
        "success":function( comment ) {
            console.log(comment);
            if(comment){
                var $commentDiv = $("#comment-media"+comment.id);
                var $commentTextarea = $("#comment-textarea"+comment.id);
                var $commentFormDiv = $("#comment-form"+comment.id);
                $commentDiv.toggleClass("d-none");
                $commentTextarea.val(comment.text);
                $commentFormDiv.toggleClass("d-none");
            }
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
    var text = $commentTextarea.val().trim();
    //If field empty stop and show error message
    if(text === ""){
        return errorField.text("Comment field cannot be empty!");
    }

    var errorField = $("#editCommentError"+id);
    var url = "/comment/update";
    var data = { id:id,text:text};
    var requestType = "POST";

    // AJAX post the updated comment to the comment controller.
    _makeAjaxPostCall(data,url,requestType,$commentTextarea);
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
    // retrieve data from reply input
    var id = event.target.getAttribute("data-id");
    var $replyInput = $("#replyInput"+id);
    var text = $replyInput.val().trim();
    //if empty return error
    if(text === ""){
        return errorField.text("Reply field cannot be empty!");
    }
    var data = {text:text, commentId:id};
    var errorField = $("#newReplyError"+id);
    var requestType = "POST";
    var url = "/reply/add";

    // AJAX post data to controller.
    _makeAjaxPostCall(data,url,requestType,$replyInput);

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
    var text =  $replyTextarea.val().trim();

    if(text === ""){
        return errorField.text("Reply field cannot be empty!");
    }
    var errorField = $("#editReplyError"+id);
    var url = "/reply/update";
    var data = { id:id,text:text}
    var requestType = "POST";
    // AJAX post the updated reply to the reply controller.
    _makeAjaxPostCall(data,url,requestType,$replyTextarea);
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
        postId = event.target.getAttribute("postId"),
        replyId = event.target.getAttribute("replyId"),
        commentId = event.target.getAttribute("commentId"),
        entityType = event.target.getAttribute("data-entity"),
        data = {author: author, postId:postId, commentId:commentId, replyId:replyId},
        requestType = "POST",
        url =  "/like/toggle-like";

    //AJAX post the data to the like controller.
        _makeAjaxPostCall(data,url,requestType,null);
});




