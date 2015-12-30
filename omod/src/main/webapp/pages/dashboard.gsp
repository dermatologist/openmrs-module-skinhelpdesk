
<% ui.decorateWith("appui", "standardEmrPage") %>

        ${ ui.includeFragment("uiframework", "helloUser") }<br>
Sinu  <img src="/openmrs/ms/uiframework/resource/skinhelpdesk/images/i_updown.jpg" alt="Smiley face" height="42" width="42">
<p>
        ${ ui.includeFragment("skinhelpdesk", "encountersToday", [start: "2015-12-27", end: "2015-12-27 23:59:59.999"]) }
</p>
<p>
        ${ ui.includeFragment("skinhelpdesk", "skinhelpdesk") }


</p>