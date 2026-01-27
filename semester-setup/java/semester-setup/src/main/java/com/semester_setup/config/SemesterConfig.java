/**
 * Data container holding semester configuration including semester name,
 * list of enrolled classes, and chosen directory template.
 * Acts as a data transfer object between CLI prompts and file/git operations.
 *
 * @author harrisonjansenva
 * @since January 22, 2026
 */
package com.semester_setup.config;
import java.util.List;

public record SemesterConfig ( 
    String semesterName,
    List<String> classes,
    DirectoryTemplate template
){}
