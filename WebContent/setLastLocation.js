/**
 *  set the last location of site
 *  expires in 20 days
 */
function setLastLocation()
{
	$.cookie('lastLocation',window.location.pathname,{expires:20});
}