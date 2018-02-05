var subject = false;
var body = false;
var leaveDays = false;
var startDate = false;
var endDate = false;

/**
 * 
 * @param {type} subjectValue
 * @returns {undefined}
 */
var bodyValidator = function (bodyValue) {

    bodyValue = bodyValue.trim();
    var bodyField = document.getElementById('apply_body');

    if (bodyValue.length !== 0) {

        body = true;
        bodyField.style.borderWidth = '2px';
        bodyField.style.borderColor = '#4ef442';
    } else {

        body = false;
        bodyField.style.borderWidth = '2px';
        bodyField.style.borderColor = '#fc0000';
    }
};


/**
 * 
 * @param {type} subjectValue
 * @returns {undefined}
 */
var subjectValidator = function (subjectValue) {

    subjectValue = subjectValue.trim();
    var subjectField = document.getElementById('apply_subject');

    if (subjectValue.length !== 0) {

        subject = true;
        subjectField.style.borderWidth = '2px';
        subjectField.style.borderColor = '#4ef442';
    } else {

        body = false;
        subjectField.style.borderWidth = '2px';
        subjectField.style.borderColor = '#fc0000';
    }
};

/**
 * 
 * @param {type} leaveDaysValue
 * @returns {undefined}
 */
var leaveDaysValidator = function (leaveDaysValue) {

    leaveDaysValue = leaveDaysValue.trim();
    var leaveDaysField = document.getElementById('leave_days');

    if (leaveDaysValue.length !== 0) {

        leaveDays = true;
        leaveDaysField.style.borderWidth = '2px';
        leaveDaysField.style.borderColor = '#4ef442';
    } else {

        leaveDays = false;
        leaveDaysField.style.borderWidth = '2px';
        leaveDaysField.style.borderColor = '#fc0000';
    }
};

/**
 * 
 * @returns {undefined}
 */
var startDateValidator = function () {

    var startDateField = document.getElementById('start_date');

    if (startDateField.value === '') {

        startDate = false;
        startDateField.style.borderColor = '#fc0000';
    } else {

        startDate = true;
        startDateField.style.borderColor = '#4ef442';
    }

};

/**
 * 
 * @returns {undefined}
 */
var endDateValidator = function () {

    var endDateField = document.getElementById('end_date');

    if (endDateField.value === '') {

        endDate = false;
        endDateField.style.borderColor = '#fc0000';
    } else {

        endDate = true;
        endDateField.style.borderColor = '#4ef442';
    }

};

/**
 * 
 * @returns {Boolean}
 */
var applicationInsertValidator = function () {

//    console.log(document.getElementById('start_date').value);
//    console.log(document.getElementById('end_date').value);

    startDateValidator();
    endDateValidator();
    subjectValidator(document.getElementById('apply_subject').value);
    bodyValidator(document.getElementById('apply_body').value);

    if (subject && body && startDate && endDate) {

        return true;
    }

    return false;
};