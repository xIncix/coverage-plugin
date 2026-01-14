package io.jenkins.plugins.coverage.metrics.steps;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.hm.hafner.coverage.Metric;
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

    private final Node result;

    QualityViewBuildAction(final Run<?, ?> run, final Node result) {
        super(run, result, false);
        this.result = result;
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

    /**
     * Gives the quality metrics.
     *
     * @return string value of the json data
     */
    @SuppressWarnings("unused")
    public String getQualityMetrics() {
        JSONObject data = new JSONObject();

        var line = result.getValue(Metric.LINE);
        var branch = result.getValue(Metric.BRANCH);
        var linesOfCode = result.getValue(Metric.LOC);

        var metrics = new JSONArray();

        if (line.isPresent()) {
            var obj = new JSONObject();
            obj.put("metric", line.get().getMetric().getDisplayName());
            obj.put("value", line.get().asDouble());
            metrics.put(obj);
        }

        if (branch.isPresent()) {
            var obj = new JSONObject();
            obj.put("metric", branch.get().getMetric().getDisplayName());
            obj.put("value", branch.get().asDouble());
            metrics.put(obj);
        }

        if (linesOfCode.isPresent()) {
            var obj = new JSONObject();
            obj.put("metric", linesOfCode.get().getMetric().getDisplayName());
            obj.put("value", linesOfCode.get().asDouble());
            metrics.put(obj);
        }
        data.put("metrics", metrics);

        return data.toString();
    }
}
