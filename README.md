# 3330Assn2

University IT Help Desk

Group members: Liam Jolles, Masen Adelman, Drew Deop
IDE used: Eclipse IDE for Java Developers
GitHub repository: https://github.com/liamjolles/3330Assn2

Design decisions
----------------
1. Can a request be resolved more than once?
   No. A second attempt throws IllegalStateException (AbstractSupportRequest).

2. Can the same request be assigned to the same technician more than once?
   No. HelpDesk.assignRequest rejects it.

3. Can one request be assigned to multiple technicians?
   No. Each request has at most one technician (HelpDesk.assignRequest).

4. NetworkIssueRequest service points:
   Weighted rule: basePoints + (affectedUsers * 2).
   affectedUsers must be positive, otherwise IllegalArgumentException is thrown.
