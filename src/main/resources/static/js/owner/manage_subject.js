const $tableSubject = $('#table-subject');

function getActionButton(name) {
  return `<button href="#">Delete</button>`;
}

// TODO: adjust request to be paginated
const dataTableProp = {
  ajax: {
    dataSrc: json => {
      json.recordsTotal = json.totalElements;
      json.recordsFiltered = json.numberOfElements;
      return json.content;
    },
    url: `${subjectsApi}/${organizationId}`,
  },
  columns: [
    {data: 'name'},
    {data: 'description'},
    {data: 'level'},
    {
      data: 'name',
      render: (data) => {
        return getActionButton(data);
      },
    },
  ],
  dom: 'ftipr',
  processing: true,
  searching: false,
  serverSide: true,
};

$(document).ready(() => {
  $tableSubject.DataTable(dataTableProp);
});
