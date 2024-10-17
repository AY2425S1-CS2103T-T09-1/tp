---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# InternBuddy User Guide

InternBuddy is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

1. Download the latest `.jar` file from [here](https://github.com/AY2425S1-CS2103T-T09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/Jane Street p/91234567 e/careers@janestreet.com a/Jane Street, block 123, #01-01` : Adds a company named `Company` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a company: `add`

Adds a company to the address book.

Format: `add n/NAME e/EMAIL [p/PHONE_NUMBER] [a/ADDRESS] [s/STATUS] [t/TAG]…​`

* `STATUS` can only take the values `INTERESTED`, `APPLIED`, `CLOSED` and will be `INTERESTED` if not specified.

<box type="tip" seamless>

**Tip:** A company can have any number of tags (including 0)
</box>

Examples:
* `add n/Apple e/contact@apple.com`
* `add n/Netflix e/contact@netflix.com p/4085403700 a/100 Winchester Circle, Los Gatos, CA s/INTERESTED`
* `add n/Google LLC t/FAANG e/contact@google.com p/1234567 t/tech`

### Listing all companies : `list`

Shows a list of all companies in the address book.

Format: `list`

### Editing a company : `edit`

Edits an existing company in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/STATUS] [t/TAG]…​`

* Edits the company at the specified `INDEX`. The index refers to the index number shown in the displayed company list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the company will be removed i.e adding of tags is not cumulative.
* You can remove all the company’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/company@example.com` Edits the phone number and email address of the 1st company to be `91234567` and `company@example.com` respectively.
*  `edit 2 n/Goggle t/` Edits the name of the 2nd company to be `Goggle` and clears all existing tags.

### Adding application record for a company: `apply`

Adds an internship application record to an existing company in the address book.

Format: `apply INDEX n/NAME d/DESCRIPTION [as/APPLICATION_STATUS]`

* Adds an application record for the company at the specified `INDEX`. The index refers to the index number shown in the displayed company list. The index **must be a positive integer** 1, 2, 3, …​
* `APPLICATION_STATUS` can only take the values `APPLIED`, `OA`, `INTERVIEWED`, `OFFERED`, `ACCEPTED`, `REJECTED`
and will take be `APPLIED` if not specified.

<box type="tip" seamless>

**Tip:** Applying to a company automatically changes its `STATUS` to `APPLIED`.
</box>

### Withdrawing application for a company: `withdraw`

Removes an internship record for an existing company in the address book.

Format: `withdraw INDEX APPLICATION_INDEX`

* Removes the application record numbered `APPLICATION_INDEX` for the company at the specified `INDEX`. 
The index refers to the index number shown in the displayed company list. The index **must be a positive integer** 1, 2, 3, …​

### Locating companies by name: `find`

Finds companies whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `inc` will match `Inc`
* The order of the keywords does not matter. e.g. `Ltd Pte` will match `Pte Ltd`
* Only the name is searched.
* Only full words will be matched e.g. `Inc` will not match `Incorporated`
* companies matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Tech Bro` will return `Good Tech`, `Bro Inc`

Examples:
* `find Inc` returns `inc` and `Apple Inc`
* `find Apple Inc` returns `Apple Inc`, `Amazon Inc`<br>
  ![result for 'find apple google'](images/findAlexDavidResult.png)

### Deleting a company : `delete`

Deletes the specified company from the address book.

Format: `delete INDEX`

* Deletes the company at the specified `INDEX`.
* The index refers to the index number shown in the displayed company list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd company in the address book.
* `find Apple` followed by `delete 1` deletes the 1st company in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format, Examples                                                                                                                                                              |
|--------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**      | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Google LLC p/22224444 e/careers@google.com a/70 Pasir Panjang Rd, #03-71, 117371 t/tech t/software` |
| **Clear**    | `clear`                                                                                                                                                                       |
| **Delete**   | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                           |
| **Edit**     | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/Meta Platforms e/jobs@meta.com`                                                     |
| **Apply**    | `apply INDEX n/NAME d/DESCRIPTION [as/APPLICATION_STATUS]`<br> e.g., `apply 1 n/Software Engineering Intern d/Uses React`                                                     |
| **Withdraw** | `withdraw INDEX APPLICATION_INDEX`<br> e.g., `withdraw 3 1`                                                                                                                   |
| **Find**     | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Apple Inc`                                                                                                                     |
| **List**     | `list`                                                                                                                                                                        |
| **Help**     | `help`                                                                                                                                                                        |
