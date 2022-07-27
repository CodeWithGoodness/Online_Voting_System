# Online_Voting_System
 
## Description
The online _voting_system is designed to make election processes more effective and less stressful. This involves the admins(election officials), voters and candidates. The candidates are to be added by the admins, hence only the admins snd voters can log in to this system.
The admins and voters have different actions they're able to perform. The database used for this project is SQL.
### Admins
1. Adding admins: only a previously registered admin can add another admin. All admins have a common log in password.
2. Add or Remove a candidate
3. Edit the admin password
4. Check the election results
### Voters
1. They can view registered candidates fir rhe election
2. Cast their vote, this is a one time action.
3. View results
4. Change password

The classes in this project includes:
1. General: This class contains the main method, thus where you run this project from. It Starts by asking if you're an Admin or just voter and in either case would be confirmed by your log in details (name and password). A new voter can as well be registered.
2. Admin : contains the getters and setters for the AdminMethods class.
3. AdminMethods: methods in this class are the actions which can be performed by admins.
4. Voter: contains the getters and setters for the VoterMethods class.
5. VoterMethods: methods in this class are the actions which can be performed by Voters.
6. Candidates: contains the getters and setters for the CandidatesMethods class.
7. CandidatesMethods: This has the register candidates method and the code to display csndidates too.
8. Election: contains getters snd setters for ElectionMethods class.
9. ElectionMethods: contains code to check for the election day and allow voters vote only once on election day. 
10. Test class: used to testing the codes in steps while working on the program.

some methods were shared by all classes such as the close and closeResultset ehich closes database connections, statements and resultset.

#### Developed interest in this project due to the ongoing election process in Nigeria, an online voting system such as this would make it possible for more people to register under a short time frame snd as well in a less stressful manner.
## Author
Goodness Okoro
       , 

