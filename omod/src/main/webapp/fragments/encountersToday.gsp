<table>
<tr>
<th>${ ui.message("Encounter.type") }</th>
        <th>${ ui.message("Encounter.datetime") }</th>
<th>${ ui.message("Encounter.location") }</th>
        <th>${ ui.message("Encounter.provider") }</th>
</tr>
    <% if (encounters) { %>
        <% encounters.each { %>
            <tr>
                <td>${ ui.format(it.encounterType) }</td>
<td>${ ui.format(it.encounterDatetime) }</td>
                <td>${ ui.format(it.location) }</td>
<td>${ ui.format(it.provider) }</td>
            </tr>
<% } %>
<% } else { %>
    <tr>
    <td colspan="4">${ ui.message("general.none") }</td>
        </tr>
    <% } %>
</table>