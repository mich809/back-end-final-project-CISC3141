package com.server.bugtracker.bug;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Scope("session")
@Repository
public interface BugRepo extends CrudRepository<Bug, Long>, JpaRepository<Bug, Long>
{

    /**
     * Gets all of the bugs from the Bug table
     * @return JSON array of bugs
     */
    @Query(value = "SELECT * FROM bug", nativeQuery = true)
    List<Bug> getBugs();

    /**
     * Update a bug
     * @param id
     * @param title
     * @param bug_description
     * @param due_date
     * @param assigned_to
     * @param created_by
     * @param severity
     * @param bug_status
     */
    @Modifying
    @Query(value = "UPDATE bug AS b SET b.id = ?1, b.title = ?2, b.bug_description = ?3,  b.due_date = ?4, " +
            "b.assigned_to = ?5, b.created_by = ?6, b.severity = ?7, b.bug_status = ?8 WHERE b.id = ?1", nativeQuery = true)
    void updateBug( @Param("id") long id, @Param("title") String title, @Param("bug_description") String bug_description,
                           @Param("due_date") String due_date, @Param("assigned_to") long assigned_to,
                           @Param("created_by") long created_by, @Param("severity") String severity, @Param("bug_status") String bug_status);

    /**
     * Finds a single bug using an id
     * @param id
     * @return Deserialized bug entry from database
     */
    @Query(value = "SELECT b.id , b.title, b.bug_description, b.due_date, b.assigned_to, b.created_by, b.severity, b.bug_status FROM bug AS b WHERE b.id = ?1" , nativeQuery = true)
    Bug getBug( @Param ("id") long id);

    /**
     * Deletes a single bug using an id
     * @param id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bug AS b WHERE b.id = ?1", nativeQuery = true)
    void deleteBug( @Param ("id") long id);



}
