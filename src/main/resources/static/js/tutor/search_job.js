const $tableSearchJob = $('#table-search-job');

function getActionButton(jobId) {
  return `<a href="${jobDetailsUrl}/${jobId}" class="btn btn-outline-primary">View</a>`;
}

// TODO: adjust request to be paginated
const dataTableProp = {
  ajax: {
    data: params => {
      params.page = $tableSearchJob.DataTable().page.info().page;
    },
    dataSrc: json => {
      json.recordsTotal = json.totalElements;
      json.recordsFiltered = json.numberOfElements;
      return json.content;
    },
    url: `${jobsApi}`,
  },
  columns: [
    {data: 'organizationName'},
    {data: 'name'},
    {data: 'age'},
    {
      data: 'jobId',
      render: (data) => {
        return getActionButton(data);
      },
    },
  ],
  dom: 'ftipr',
  processing: true,
  searching: true,
  serverSide: true,
};

$(document).ready(() => {
  $tableSearchJob.DataTable(dataTableProp);
});
