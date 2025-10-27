package io.jenkins.plugins.coverage.metrics.steps;

import edu.hm.hafner.coverage.Node;

import java.io.Serial;

import org.kohsuke.stapler.StaplerProxy;

import hudson.model.Run;

import io.jenkins.plugins.util.AbstractXmlStream;
import io.jenkins.plugins.util.BuildAction;
import io.jenkins.plugins.util.JobAction;

/**
 * Java doc for Quality View.
 */
public final class QualityViewBuildAction extends BuildAction<Node> implements StaplerProxy {
    @Serial
    private static final long serialVersionUID = 2486659311930728733L;

    QualityViewBuildAction(final Run<?, ?> run, final Node result) {
        super(run, result, false);
    }

    @Override
    protected AbstractXmlStream<Node> createXmlStream() {
        return null;
    }

    @Override
    protected JobAction<? extends BuildAction<Node>> createProjectAction() {
        return null;
    }

    @Override
    protected String getBuildResultBaseName() {
        return "quality.xml";
    }

    @Override
    public String getIconFileName() {
        return "symbol-diamond-outline plugin-ionicons-api";
    }

    @Override
    public String getDisplayName() {
        return "Quality View";
    }

    @Override
    public String getUrlName() {
        return "quality-view";
    }

    @Override
    public Object getTarget() {
        return this;
    }
}
