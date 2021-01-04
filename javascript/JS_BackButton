//ON BACK BUTTON PRESS JAVA SCRIPT REDIRECT TO SPECIFIC PAGE/URL
jQuery(document).ready(function($) {

      if (window.history && window.history.pushState) {

        $(window).on('popstate', function() {
          var hashLocation = location.hash;
          var hashSplit = hashLocation.split("#!/");
          var hashName = hashSplit[1];

          if (hashName !== '') {
            var hash = window.location.hash;
            if (hash === '') {
              alert('Back button was pressed.');
                window.location='www.example.com';
                return false;
            }
          }
        });

        window.history.pushState('forward', null, './#forward');
      }

    });
