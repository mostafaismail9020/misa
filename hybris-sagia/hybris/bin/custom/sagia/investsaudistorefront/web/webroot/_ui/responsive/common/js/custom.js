$(document).ready(function () {
    window.onbeforeunload = function () {
        window.scrollTo(0, 0);
    }
    var overflow = "hidden";
    var footerHeight
    $(".float-button").click(function () { 
        $(".popup").toggleClass("popup-up");
        $(".float-button").toggleClass("float-button-up");
        $(".full-bg").fadeToggle();
        $(this).html($(this).text() == 'Invest Now' ? "<img width='40' src='../images/close.png'>": 'Invest Now');
        /*
        $("body").css("overflow", overflow); 
        $(this).html($(this).text() == 'Invest Now' ? "<img width='40' src='./images/close.png'>" : 'Invest Now');
        $(this).html($(this).text() == "<img width='40' src='./img/close.png'>" ? 'Invest Now' : 'Invest Now');*/
        overflow = (overflow == "hidden") ? "visible" : "hidden";
    });

    $(window).on('scroll', function () {
        footerHeight = $("#footer").offset().top - 800;
        console.log($(this).scrollTop())
        if ($(this).scrollTop() >= footerHeight) {
            $('.float-button').css("opacity", "1");
        }
        else {
            $('.float-button').css("opacity", "1");
        }
    });
});

$(function() {

    var siteSticky = function() {
          $(".js-sticky-header").sticky({topSpacing:0});
          /*$(this).html($(this).text() == '<img src="img/INV_logo-en.png" class="img-responsive">' ? '<img src="img/INV_logo-en.png" class="img-responsive" style="width:20px;">': '<img src="img/INV_logo-en.png" class="img-responsive" style="width:20px;">');*/
      };
      siteSticky();
  
      var siteMenuClone = function() {
  
          $('.js-clone-nav').each(function() {
              var $this = $(this);
              $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
          });
  
  
          setTimeout(function() {
              
              var counter = 0;
        $('.site-mobile-menu .has-children').each(function(){
          var $this = $(this);
          
          $this.prepend('<span class="arrow-collapse collapsed">');
  
          $this.find('.arrow-collapse').attr({
            'data-toggle' : 'collapse',
            'data-target' : '#collapseItem' + counter,
          });
  
          $this.find('> ul').attr({
            'class' : 'collapse',
            'id' : 'collapseItem' + counter,
          });
  
          counter++;
  
        });
  
      }, 1000);
  
          $('body').on('click', '.arrow-collapse', function(e) {
        var $this = $(this);
        if ( $this.closest('li').find('.collapse').hasClass('show') ) {
          $this.removeClass('active');
        } else {
          $this.addClass('active');
        }
        e.preventDefault();  
        
      });
  
          $(window).resize(function() {
              var $this = $(this),
                  w = $this.width();
  
              if ( w > 768 ) {
                  if ( $('body').hasClass('offcanvas-menu') ) {
                      $('body').removeClass('offcanvas-menu');
                  }
              }
          })
  
          $('body').on('click', '.js-menu-toggle', function(e) {
              var $this = $(this);
              e.preventDefault();
  
              if ( $('body').hasClass('offcanvas-menu') ) {
                  $('body').removeClass('offcanvas-menu');
                  $this.removeClass('active');
              } else {
                  $('body').addClass('offcanvas-menu');
                  $this.addClass('active');
              }
          }) 
  
          // click outisde offcanvas
          $(document).mouseup(function(e) {
          var container = $(".site-mobile-menu");
          if (!container.is(e.target) && container.has(e.target).length === 0) {
            if ( $('body').hasClass('offcanvas-menu') ) {
                      $('body').removeClass('offcanvas-menu');
                  }
          }
          });
      }; 
      siteMenuClone();
  
  }); 