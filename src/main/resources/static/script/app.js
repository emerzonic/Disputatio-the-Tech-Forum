console.log("connected");
autosize(document.getElementById("commentInput"));
autosize(document.getElementById("postTitleInput"));
autosize(document.getElementById("postTextInput"));
autosize(document.getElementById("replyInput"));
autosize(document.getElementById("editPostTitleInput"));
autosize(document.getElementById("editPostTextInput"));


//submitting new comment
$("#commentForm").submit(function(event) {
    event.preventDefault();
    // create new comment object to be submitted

      var text = $("#commentInput").val();
      var  postId = $("#postId").val();

    // AJAX post the data to the comment controller.
    var url = "/comment/add";
    var data = {text:text, postId:postId};
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': function (data) {
            console.log(data)
            setTimeout(function(){
                location.reload();
            }, 2000);
        }
    });
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
              var $commentFormDiv = $("#comment-form"+comment.id);
              var $commentTextarea = $("#comment-textarea"+comment.id);
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
$(".comment-edit-Form").submit(function(event) {
    console.log('submit');
    event.preventDefault();
    console.log(event.target)
    // var id = event.target.getAttribute("commentId");

    // create new comment object to be submitted

    // var text =  $("#comment-textarea"+id).val();
    // var id = event.target.getAttribute("commentId");
    //
    // // AJAX post the data to the comment controller.
    // var url = "/comment/add";
    // var data = {text:text, postId:postId};
    // $.ajax({
    //     headers: {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json'
    //     },
    //     'type': 'POST',
    //     'url': url,
    //     'data': JSON.stringify(data),
    //     'dataType': 'json',
    //     'success': function (data) {
    //         console.log(data)
    //         setTimeout(function(){
    //             location.reload();
    //         }, 2000);
    //     }
    // });
});







//deleting comment
$(document).on("click", ".commentDelete", function(event) {
    var id = event.target.getAttribute("commentId");
    console.log(id);
    $.ajax({
        method: "GET",
        url: "/comment/delete/"+id
    })
        .done(function( msg ) {
            console.log( msg );
        });
    setTimeout(function(){
        location.reload();
    }, 2000);
});


// $("#cancelButton").click(function() {
// 	$("#commentInput").val("");
// });
//
// $("#reply-link").click(function() {
// 	$("#replyForm").removeClass("d-none");
// });



$(function() {
	$(document).scroll(function() {
		var $nav = $("#mainNav");
		if ($(window).scrollTop() > $nav.height()) {
			$nav.addClass("bg-dark");
		} else {
			$nav.removeClass("bg-dark");
		}
	})
});