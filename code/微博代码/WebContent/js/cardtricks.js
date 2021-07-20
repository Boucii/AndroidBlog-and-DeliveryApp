var dealer_in = function () {
    animate_done=0;
    //$(".card").addClass("AnimateFast");
    $(".blog").css("display", "none");
    $("#card1").addClass("animated");
    $("#card2").addClass("animated");
    $("#card1").css("display", "none");
    $("#card2").addClass("fadeInLeftBig");
    setTimeout(
        function () {
            $("#card1").addClass("fadeInLeftBig");
            $("#card1").css("display", "inline");
        }, 200);

    setTimeout(
        function () {
            $("#card2").removeClass("fadeInLeftBig");
        }, 200 + 5);

    setTimeout(
        function () {
            $("#card1").removeClass("fadeInLeftBig");
            //$(".card").removeClass("AnimateFast");
            $(".blog").css("display", "inline");
            animate_done=1;
        }, 600);

}