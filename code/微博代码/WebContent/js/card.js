var animate_done = 1;
$(document).ready(function () {
    //$("#card3").height("0px");
    //$("#card4").height("0px");
    //$("#card3").css('display','none');
    //$("#card4").css('display','none');
    $("#card1").on("swipeleft", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card1").addClass("animated rotateOutUpLeft back-card");
            setTimeout(function () {
                $("#card1").removeClass("animated rotateOutUpLeft front-card");
            }, 1000);

            $("#card2").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card2").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
    $("#card2").on("swipeleft", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card2").addClass("animated rotateOutUpLeft back-card");
            setTimeout(function () {
                $("#card2").removeClass("animated rotateOutUpLeft front-card");
            }, 1000);

            $("#card1").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card1").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
    $("#card1").on("swiperight", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card1").addClass("animated rotateOutUpRight back-card");
            setTimeout(function () {
                $("#card1").removeClass("animated rotateOutUpRight front-card");
            }, 1000);

            $("#card2").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card2").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
    $("#card2").on("swiperight", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card2").addClass("animated rotateOutUpRight back-card");
            setTimeout(function () {
                $("#card2").removeClass("animated rotateOutUpRight front-card");
            }, 1000);

            $("#card1").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card1").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });

    $("#card3").on("swipeleft", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card3").addClass("animated rotateOutUpLeft back-card");
            setTimeout(function () {
                $("#card3").removeClass("animated rotateOutUpLeft front-card");
            }, 1000);

            $("#card4").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card4").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
    $("#card4").on("swipeleft", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card4").addClass("animated rotateOutUpLeft back-card");
            setTimeout(function () {
                $("#card4").removeClass("animated rotateOutUpLeft front-card");
            }, 1000);

            $("#card3").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card3").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
    $("#card3").on("swiperight", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card3").addClass("animated rotateOutUpRight back-card");
            setTimeout(function () {
                $("#card3").removeClass("animated rotateOutUpRight front-card");
            }, 1000);

            $("#card4").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card4").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
    $("#card4").on("swiperight", function () {
        if (animate_done == 1) {
            animate_done =0;
            $("#card4").addClass("animated rotateOutUpRight back-card");
            setTimeout(function () {
                $("#card4").removeClass("animated rotateOutUpRight front-card");
            }, 1000);

            $("#card3").addClass("animated zoomIn front-card");
            setTimeout(function () {
                $("#card3").removeClass("animated zoomIn back-card");
                animate_done = 1;
            }, 1000);
        }
    });
});