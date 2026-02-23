package com.tmdt.base.shared.config.auth;

import com.tmdt.base.domain.model.Role;
import com.tmdt.base.domain.model.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final UserAccount user;

    public CustomUserDetails(UserAccount user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        Set<Role> roles = user.getRoles();
        
        // Add Role authorities
        roles.stream()
                .map(Role::getRoleName)
                .map(roleName -> roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .forEach(authorities::add);
        
        // Add Permission authorities from role_permission
        roles.stream()
                .flatMap(role -> role.getRolePermissions().stream())
                .filter(rolePermission -> rolePermission.getPermission() != null)
                .map(rolePermission -> rolePermission.getPermission().getPermissionName())
                .map(SimpleGrantedAuthority::new)
                .forEach(authorities::add);
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        Boolean active = user.getIsActive();
        return active == null || active;
    }

    public Long getUserId() {
        return user.getId();
    }
}

