function include(e) {
    document.write('<script src="' + e + '"></script>')
}

function isIE() {
    var e = navigator.userAgent.toLowerCase();
    return -1 != e.indexOf("msie") ? parseInt(e.split("msie")[1]) : !1
}
include("../resources/js/jquery.cookie.js"), include("../resources/js/jquery.easing.1.3.js"),
    function(e) {
        isIE() && isIE() < 11 && (include("../resources/js/pointer-events.js"), e("html").addClass("lt-ie11"), e(document).ready(function() {
            PointerEventsPolyfill.initialize({})
        }))
    }(jQuery),
    function(e) {
        var n = e("html");
        n.hasClass("desktop") && (include("../resources/js/tmstickup.js"), e(document).ready(function() {
            e("#stuck_container").TMStickUp({})
        }))
    }(jQuery),
    function(e) {
        var n = e("html");
        n.hasClass("desktop") && (include("../resources/js/jquery.ui.totop.js"), e(document).ready(function() {
            e().UItoTop({
                easingType: "easeOutQuart",
                containerClass: "toTop fa fa-angle-up"
            })
        }))
    }(jQuery),
    function(e) {
        var n = e("[data-equal-group]");
        n.length > 0 && include("../resources/js/jquery.equalheights.js")
    }(jQuery),
    function(e) {
        (new Date).getFullYear();
        e(document).ready(function() {
            e("#copyright-year").text((new Date).getFullYear())
        })
    }(jQuery),
    function(e) {
        include("../resources/js/superfish.js")
    }(jQuery),
    function(e) {
        include("../resources/js/jquery.rd-navbar.js")
    }(jQuery),
    function(e) {
        var n = e("html");
        (-1 == navigator.userAgent.toLowerCase().indexOf("msie") || isIE() && isIE() > 9) && n.hasClass("desktop") && (include("../resources/js/wow.js"), e(document).ready(function() {
            (new WOW).init()
        }))
    }(jQuery),
    function(e) {
        var n = e(".rd-mailform");
        n.length > 0 && (include("../resources/js/mailform/jquery.form.min.js"), include("../resources/js/mailform/jquery.rd-mailform.min.js"), e(document).ready(function() {
            var n = e(".rd-mailform");
            n.length && n.rdMailForm({
                validator: {
                    constraints: {
                        "@LettersOnly": {
                            message: "Use apenas letras!"
                        },
                        "@NumbersOnly": {
                            message: "Use apenas números!"
                        },
                        "@NotEmpty": {
                            message: "Este campo não pode estar vazio!"
                        },
                        "@Email": {
                            message: "Informe um e-mail válido!"
                        },
                        "@Phone": {
                            message: "Informe um telefone válido!"
                        },
                        "@Date": {
                            message: "Use o formato DD/MM/AAAA!"
                        },
                        "@SelectRequired": {
                            message: "Escolha uma opção!"
                        }
                    }
                }
            }, {
                MF000: "Sent",
                MF001: "Recipients are not set!",
                MF002: "Form will not work locally!",
                MF003: "Please, define email field in your form!",
                MF004: "Please, define type of your form!",
                MF254: "Something went wrong with PHPMailer!",
                MF255: "There was an error submitting the form!"
            })
        }))
    }(jQuery),
    function(e) {
        var n = e("#camera");
        n.length > 0 && (isIE() && isIE() > 9 || include("../resources/js/jquery.mobile.customized.min.js"), include("../resources/js/camera.js"), e(document).ready(function() {
            n.camera({
                autoAdvance: !0,
                height: "30.53658536585366%",
                minHeight: "350px",
                pagination: !0,
                thumbnails: !1,
                playPause: !1,
                hover: !1,
                loader: "none",
                navigation: !0,
                navigationHover: !1,
                mobileNavHover: !1,
                fx: "simpleFade"
            })
        }))
    }(jQuery),
    function(e) {
        include("../resources/js/jquery.rd-parallax.js")
    }(jQuery),
    function(e) {
        var n = e(".radial-progress");
        n.length > 0 && include("../resources/js/raphael/jquery.radial-progress-bar.js")
    }(jQuery),
    function(e) {
        var n = e(".accordion , .accordion1");
        n.length > 0 && (include("../resources/js/jquery.ui.accordion.min.js"), e(document).ready(function() {
            n.accordion({
                active: !1,
                header: ".accordion_header , .accordion_header1",
                heightStyle: "content",
                collapsible: !0
            })
        }))
    }(jQuery), $(function() {
        var e = document.querySelector && document.querySelector('meta[name="viewport"]'),
            n = navigator.userAgent,
            i = function() {
                e.content = "width=device-width, minimum-scale=0.25, maximum-scale=1.6, initial-scale=1.0"
            },
            t = function() {
                e && /iPhone|iPad/.test(n) && !/Opera Mini/.test(n) && (e.content = "width=device-width, minimum-scale=1.0, maximum-scale=1.0", document.addEventListener("gesturestart", i, !1))
            };
        if (t(), void 0 != window.orientation) {
            var a = /ipod|ipad|iphone/gi,
                r = n.match(a);
            r || $(".sf-menus li").each(function() {
                $(">ul", this)[0] && $(">a", this).toggle(function() {
                    return !1
                }, function() {
                    window.location.href = $(this).attr("href")
                })
            })
        }
    });
$(function() {
    $("a").on('click', function(event) {
        if (this.hash !== "") {
            event.preventDefault();
            var hash = this.hash;
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 800, function() {
                window.location.hash = hash
            })
        }
    })
});
var ua = navigator.userAgent.toLocaleLowerCase(),
    regV = /ipod|ipad|iphone/gi,
    result = ua.match(regV),
    userScale = "";
result || (userScale = ",user-scalable=0"), document.write('<meta name="viewport" content="width=device-width,initial-scale=1.0' + userScale + '">')