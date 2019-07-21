const $tableJobApplication = $('#table-job-application');

function getTutorLink(tutorName, jobAppId) {
  return `<a href="${manageJobApplicationDetailsUrl}/${jobAppId}" id="view-job-applicant-${jobAppId}-link" data-job-app-id="${jobAppId}">${tutorName}</a>`;
}

function getActionButton(jobAppId) {
  return `<button id="approve-job-${jobAppId}-button" class="btn btn-outline-primary" data-job-app-id="${jobAppId}">Approve</button>`;
}

const jobApplicationUrl = `/api/organizations/${organizationId}/job_applications`;

const token = $('[name="_csrf"]').val();

const patchApprove = jobAppId => {
  $.ajax({
    beforeSend: (request) => request.setRequestHeader('X-CSRF-Token', token),
    contentType: 'application/json',
    data: JSON.stringify({jobAppId: jobAppId}),
    method: 'PATCH',
    success: location.reload(),
    url: `${jobApplicationUrl}/${jobAppId}`,
  });
};

const registerApproveButtonEvent = () => {
  Array.from($('button[id^=approve-job]')).forEach(button => {
    const jQButtonApprove = $(button);

    jQButtonApprove.on('click', (e) => {
      const jobAppId = $(e.currentTarget).data('job-app-id');

      patchApprove(jobAppId);
    });
  });
};

const dataTableProp = {
  ajax: {
    dataSrc: json => {
      json.recordsTotal = json.length;
      json.recordsFiltered = json.length;
      return json;
    },
    url: jobApplicationUrl,
  },
  columns: [
    {data: 'jobName'},
    {data: 'tutorName',
      render: (tutorName, type, row) => {
        return getTutorLink(tutorName, row.jobAppId);
      }},
    {
      data: 'jobAppId',
      render: (jobAppId) => {
        return getActionButton(jobAppId);
      },
    },
  ],
  dom: 'ftipr',
  drawCallback: registerApproveButtonEvent,
  processing: true,
  searching: false,
  serverSide: true,
};

$(document).ready(() => {
  $tableJobApplication.DataTable(dataTableProp);
});
