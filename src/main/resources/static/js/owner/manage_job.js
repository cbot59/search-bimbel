const $tableJob = $('#table-job');

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
    url: `${jobsApi}/${organizationId}`,
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
