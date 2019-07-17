const $tableJobApplication = $('#table-job-application');

function getActionButton(jobAppId) {
  // TODO: link to approve jobApp
  return `<button href="#" class="btn btn-outline-primary">Approve</button>`;
}

const dataTableProp = {
  ajax: {
    dataSrc: json => {
      json.recordsTotal = json.length;
      json.recordsFiltered = json.length;
      return json;
    },
    url: `/api/organizations/${organizationId}/job_applications`,
  },
  columns: [
    {data: 'jobName'},
    {data: 'tutorName'},
    {
      data: 'jobAppId',
      render: (jobAppId) => {
        return getActionButton(jobAppId);
      },
    },
  ],
  dom: 'ftipr',
  processing: true,
  searching: false,
  serverSide: true,
};

$(document).ready(() => {
  $tableJobApplication.DataTable(dataTableProp);
});
