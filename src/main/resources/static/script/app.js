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


//submitting new comment
$(document).on("click", ".commentDetele", function(event) {
    // event.preventDefault();
    console.log(event);
    console.log(this);

    var id = event.target.getAttribute("data-commentId");
    console.log(id);
    // setTimeout(function(){
    //     location.reload();
    // }, 2000);
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