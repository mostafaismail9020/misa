ACC.casblogaddon = {
    _autoload: [
        'bindAll'
    ],

    bindAll: function () {
        this.checkIfEventIsVisible();
        this.saveNewsLetter();
        this.saveNewsLetterBottom();
        this.facebookShareButton();
        this.searchPosts();
        this.subscribeNewsLetter();
        this.bindSideSubscriptionButtons();
        this.bindSideSubscriptionButtonClickEvent();
    },

    checkIfEventIsVisible : function(){
        var path = this.pathname || window.location.pathname;
        var index = path.indexOf("/events/");
        var index2 = path.indexOf("/searchResults");

        if(index2 !== -1 ){
            return;
        }

        if(index !== -1){
            var element = document.getElementsByClassName("blogPostDiv");

            if(element.length == 0){

                var eventsIndexPage = document.getElementById('eventsIndexPage')

                //blog page exist but component is empty redirect to index page
                if(eventsIndexPage != null){
                    window.location.href = eventsIndexPage.value;
                }
            }
        }
    },

    facebookShareButton: function () {
        var url = window.location;
        $('.fb-share-button').attr('data-href', url);
    },
    saveNewsLetter: function () {

        $("#newsletterFrom").submit(function (event) {
            var isvalidate = $("#newsletterFrom")[0].checkValidity();
            if (isvalidate) {
                var subject =window.location.pathname.split("/").pop();
                var comments = $("#comments", this).val();
                var blogPostId = document.getElementById('eventDetailForNewsletter').value;

                event.preventDefault();
                ACC.casblogaddon.submitForm(subject, comments, blogPostId);
            }
        });
    },
    saveNewsLetterBottom: function () {
        $("#newsletterFromBottom").submit(function (event) {
            var isvalidate = $("#newsletterFromBottom")[0].checkValidity();
            if (isvalidate) {
                var fname = $("input[name='fname']", this).val();
                var lname = $("input[name='lname']", this).val();
                var email = $("input[name='email']", this).val();

                event.preventDefault();
                ACC.casblogaddon.submitForm(fname, lname, email);
            }
        });
    },
    submitForm: function (subject, comments, blogPostId) {
        var self = this;
        var newsletterUrl = document.getElementById('newsletterUrl').value;
        $.ajax({
            url: newsletterUrl,
            type: 'POST',
            async: false,
            data: {
                subject: subject,
                comments: comments,
                blogPostId: blogPostId
            },
            success: function (data) {
                // $.colorbox({
                //     inline: true,
                //     href: "#newsLetterSuccess",
                //     transition: "none"
                // });

                if (data === "success") {
                    self.newsletterStatusModal($('#newsLetterSuccess'));
                } else {
                    self.newsletterStatusModal($('#newsLetterError'));
                }

                document.getElementById('newsletterFrom').reset();
                // document.getElementById('newsletterFromBottom').reset();
            },
            error: function (xht, textStatus, ex) {
                self.newsletterStatusModal($('#newsLetterError'));
            }
        });
    },

    newsletterStatusModal: function (element) {
        //clone and select a disposable parent element, so the wrapper can be copied too.
        var statusHtml = element.clone().wrap('</p>').parent();
        $('#newsletterStatusModal').find('.modal-body').html(statusHtml.html());
        $('#newsletterStatusModal').modal('show');
    },

    searchPosts: function () {
        var searchArea = $('.blog--search-area');

        if (searchArea.length > 0) {
            var searchBtn = searchArea.find('.blog--search-btn');

            searchBtn.on('click', function () {
                var searchInput = searchArea.find('.blog--search-input').val();

                if (searchInput) {
                    searchArea.find('form.blog--search-form').submit();
                } else {
                    ACC.casblogaddon.showFormError();
                }
            })
        }
    },
    showFormError: function () {
        var searchAreaFormGroup = $('.blog--search-area .form-group');

        if (!searchAreaFormGroup.hasClass('form_field_error')) {
            searchAreaFormGroup.addClass('form_field_error');
        }
    },
    
    
    subscribeNewsLetter: function () {
        $("#subscribeNewsletter").on('submit', function (event) {
            var isvalidate = $("#subscribeNewsletter")[0].checkValidity();
            if (isvalidate) {
                var eventDetailForNewsletter = $("input[name='eventDetailForNewsletter']", this).val();
                var subscriptionFlag = $("input[name='subscriptionFlag']", this).val();

                event.preventDefault();
                ACC.casblogaddon.submitSubscriptionForm(eventDetailForNewsletter, subscriptionFlag);
            }
        });
    },
    submitSubscriptionForm: function (eventDetailForNewsletter,subscriptionFlag) {
        var self = this;
    	var eventDetail = eventDetailForNewsletter;
    	var newsletterSubscriptionUrl;
    	if(subscriptionFlag === "true")
		{
    		newsletterSubscriptionUrl = $('#newsletterUnSubscribeUrl').val();
		}
    	else
		{
    		newsletterSubscriptionUrl = $('#newsletterSubscribeUrl').val();
		}

        $.ajax({
            url: newsletterSubscriptionUrl,
            type: 'POST',
            async: false,
            data: {
                eventDetail: eventDetail
            },
            success: function (data) {
                if (subscriptionFlag === "true") {
                    self.newsletterStatusModal($('#newsLetterUnsubscribeSuccess'));
                    ACC.casblogaddon.bindResultSubscribeEvent(false);
                } else {
                    self.newsletterStatusModal($('#newsLetterSubscribeSuccess'));
                    ACC.casblogaddon.bindResultSubscribeEvent(true);
                }
            },
            error: function (xht, textStatus, ex) {
                self.newsletterStatusModal($('#newsLetterSubscribeError'));
            }
        });
    },

    bindResultSubscribeEvent: function (isSubscribed) {
        if (isSubscribed) {
            $("#unSubscribe2NewsletterButton").show();
            $("#subscribe2NewsletterButton").hide();
            $('#subscriptionFlag').val("true");
            $('.newsletter-side-area').find('button').hide();
            $('.newsletter-side-area').find('button[data-action=unsubscribe]').show();
        } else {
            $("#unSubscribe2NewsletterButton").hide();
            $("#subscribe2NewsletterButton").show();
            $('#subscriptionFlag').val("false");
            $('.newsletter-side-area').find('button').hide();
            $('.newsletter-side-area').find('button[data-action=subscribe]').show();
        }

        $('#subscriptionFlag').val(isSubscribed);
    },

    bindSideSubscriptionButtons: function () {
        if ($('#subscriptionFlag').length > 0) {
            var isSubscribed = $('#subscriptionFlag').val().toLowerCase() === "true";

            if (isSubscribed) {
                $('.newsletter-side-area button[data-action=unsubscribe]').show();
            } else {
                $('.newsletter-side-area button[data-action=subscribe]').show();
            }
        }
    },

    bindSideSubscriptionButtonClickEvent: function () {
        if ($('#subscriptionFlag').length > 0) {
            var newsletterArea = $('.newsletter-side-area');

            newsletterArea.find('button').filter('[data-action=subscribe]').on('click', function () {
                $('#subscribe2NewsletterButton').trigger('click');
            });

            newsletterArea.find('button').filter('[data-action=unsubscribe]').on('click', function () {
                $('#unSubscribe2NewsletterButton').trigger('click');
            });
        }
    }
}