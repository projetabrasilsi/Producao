// Sidebar
$('#omega-menu-button').off('click').on('click', function(e) {
            $(this).toggleClass('active');

            if(window.innerWidth > 1024) {
                $(document.body).children('.wrapper').toggleClass('sidebar-inactive-l');
                
                if($(document.body).children('.wrapper').hasClass('sidebar-inactive-l')) {
                    $(document.body).children('.wrapper').removeClass('sidebar-active-m');
                }
            }
            else {
                $(document.body).children('.wrapper').toggleClass('sidebar-active-m');
                
                if($(document.body).children('.wrapper').hasClass('sidebar-active-m')) {
                    $(document.body).children('.wrapper').removeClass('sidebar-inactive-l');
                }
            }
            
            $('#topbar-icons').removeClass('topbar-icons-visible');
            e.preventDefault();
 
  });
//Profile do sidebar
$('#profile-button').off('click').on('click', function(e) {
    var profileMenu = $(this).next('ul');
    if(profileMenu.is(':visible')) {
        profileMenu.slideUp();
        $this.clearProfileMenuState();
    }
    else {
        profileMenu.slideDown();
        $this.saveProfileMenuState();
    }
    
    e.preventDefault();
});
 

