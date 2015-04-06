/**
 *  set the last location of site
 *  expires in 20 days
 */
function setCookie()
{
	$.cookie('lastSite',window.location.pathname,{expires:20});
}