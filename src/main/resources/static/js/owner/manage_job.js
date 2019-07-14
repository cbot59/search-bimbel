const $tableJob = $('#table-job');

function getActionButton(name) {
  return `<button href="#">Delete</button>`;
}

const dataTableProp = {
  ajax: {
    data: params => {
      params.page = $tableJob.DataTable().page.info().page;
    },
    dataSrc: json => {
      json.recordsTotal = json.totalElements;
      json.recordsFiltered = json.numberOfElements;
      return json.content;
    },
    url: `/api/organizations/${organizationId}/jobs`,
  },
  columns: [
    {data: 'name'},
    {data: 'age'},
    {data: 'otherNote'},
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
  $tableJob.DataTable(dataTableProp);
});
