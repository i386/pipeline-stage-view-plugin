/*
 * Copyright (C) 2013 CloudBees Inc.
 *
 * All rights reserved.
 */
package com.cloudbees.workflow.ui.view;

import hudson.Extension;
import hudson.model.Action;
import jenkins.model.Jenkins;
import jenkins.model.TransientActionFactory;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;

import java.util.Collection;
import java.util.Collections;

/**
 * {@link WorkflowJob} ui extension point {@link Action}.
 *
 * @author <a href="mailto:tom.fennelly@gmail.com">tom.fennelly@gmail.com</a>
 */
public class WorkflowStageViewAction implements Action {

    public final WorkflowJob target;

    private WorkflowStageViewAction(WorkflowJob job) {
        this.target = job;
    }

    @Override
    public String getDisplayName() {
        return "Full Stage View";
    }

    @Override
    public String getUrlName() {
        return "workflow-stage";
    }

    public boolean getHasBlueOcean() {
        Jenkins jenkins = Jenkins.getInstance();
        if (jenkins == null) {
            throw new IllegalStateException("Jenkins not started");
        }
        return jenkins.getPluginManager().getPlugin("blueocean") == null;
    }

    @Override
    public String getIconFileName() {
        return "package.png";
    }

    @Extension
    public static class Factory extends TransientActionFactory<WorkflowJob> {

        @Override
        public Class<WorkflowJob> type() {
            return WorkflowJob.class;
        }

        @Override
        public Collection<? extends Action> createFor(WorkflowJob target) {
            return Collections.singleton(new WorkflowStageViewAction(target));
        }
    }
}
