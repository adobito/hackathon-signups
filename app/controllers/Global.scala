import play.api._
import play.api.mvc._
import play.filters.headers.SecurityHeadersFilter

object Global extends WithFilters(SecurityHeadersFilter()) with GlobalSettings {
  // onStart, onStop etc...
}