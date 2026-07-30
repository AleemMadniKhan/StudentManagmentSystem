package com.StudentManagmentSystem.SMS.model;

import java.util.Set;

public enum Role {
    
    ROLE_STUDENT(Set.of(
            Permissions.GET_COURSE,
            Permissions.GET_ALL_COURSES,
            Permissions.GET_ENROLLMENT,
            Permissions.GET_MARKS
    )),
    ROLE_ADMIN(Set.of(Permissions.values())),
    ROLE_TEACHER(Set.of(
    Permissions.ADD_MARKS,
    Permissions.GET_ALL_MARKS,
    Permissions.GET_All_STUDENT,
    Permissions.GET_STUDENT,
    Permissions.GET_ALL_ENROLLMENT,
    Permissions.GET_ENROLLMENT,
    Permissions.GET_MARKS,
    Permissions.UPDATE_MARKS,
    Permissions.DELETE_MARKS,
    Permissions.GET_COURSE,          
    Permissions.GET_ALL_COURSES,     
    Permissions.GET_SECTION,         
    Permissions.GET_ALL_SECTIONS,     
    Permissions.GET_ALL_COURSES_OFFERING,
    Permissions.GET_COURSES_OFFERING
    ));

    private Set<Permissions> permissions;
    Role(Set<Permissions> of){
        this.permissions = of;
    }

    public Set<Permissions> getPermissions(){
        return permissions;
    }
}
