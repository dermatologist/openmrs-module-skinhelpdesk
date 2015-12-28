
<% ui.decorateWith("appui", "standardEmrPage") %>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.5/angular.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.5.0/fabric.min.js"></script>

        ${ ui.includeFragment("uiframework", "helloUser") }<br>
Sinu  <img src="/openmrs/ms/uiframework/resource/skinhelpdesk/images/i_updown.jpg" alt="Smiley face" height="42" width="42">
<p>
        ${ ui.includeFragment("skinhelpdesk", "encountersToday", [start: "2015-12-27", end: "2015-12-27 23:59:59.999"]) }
</p>

