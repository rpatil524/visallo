package org.visallo.web.routes.vertex;

import com.google.inject.Inject;
import com.v5analytics.webster.ParameterizedHandler;
import com.v5analytics.webster.annotations.Handle;
import com.v5analytics.webster.annotations.Optional;
import com.v5analytics.webster.annotations.Required;
import org.vertexium.Authorizations;
import org.vertexium.Graph;
import org.vertexium.Vertex;
import org.visallo.core.exception.VisalloAccessDeniedException;
import org.visallo.core.exception.VisalloException;
import org.visallo.core.model.user.UserRepository;
import org.visallo.core.model.workspace.WorkspaceRepository;
import org.visallo.core.user.User;
import org.visallo.core.util.ClientApiConverter;
import org.visallo.web.clientapi.model.ClientApiVertexMultipleResponse;
import org.visallo.web.parameterProviders.AuthorizationsParameterProviderFactory;
import org.visallo.web.parameterProviders.VisalloBaseParameterProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

import static org.vertexium.util.IterableUtils.toIterable;

public class VertexMultiple implements ParameterizedHandler {
    private final Graph graph;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;

    @Inject
    public VertexMultiple(
            final Graph graph,
            final UserRepository userRepository,
            final WorkspaceRepository workspaceRepository
    ) {
        this.graph = graph;
        this.userRepository = userRepository;
        this.workspaceRepository = workspaceRepository;
    }

    @Handle
    public ClientApiVertexMultipleResponse handle(
            HttpServletRequest request,
            @Required(name = "vertexIds[]") String[] vertexIdsParam,
            @Optional(name = "fallbackToPublic", defaultValue = "false") boolean fallbackToPublic,
            User user
    ) throws Exception {
        HashSet<String> vertexStringIds = new HashSet<>(Arrays.asList(vertexIdsParam));
        GetAuthorizationsResult getAuthorizationsResult = getAuthorizations(request, fallbackToPublic, user);
        String workspaceId = getWorkspaceId(request);

        Iterable<String> vertexIds = toIterable(vertexStringIds.toArray(new String[vertexStringIds.size()]));
        Iterable<Vertex> graphVertices = graph.getVertices(vertexIds, ClientApiConverter.SEARCH_FETCH_HINTS, getAuthorizationsResult.authorizations);
        ClientApiVertexMultipleResponse result = new ClientApiVertexMultipleResponse();
        result.setRequiredFallback(getAuthorizationsResult.requiredFallback);
        for (Vertex v : graphVertices) {
            result.getVertices().add(ClientApiConverter.toClientApiVertex(v, workspaceId, getAuthorizationsResult.authorizations));
        }
        return result;
    }

    private GetAuthorizationsResult getAuthorizations(HttpServletRequest request, boolean fallbackToPublic, User user) {
        GetAuthorizationsResult result = new GetAuthorizationsResult();
        result.requiredFallback = false;
        try {
            result.authorizations = AuthorizationsParameterProviderFactory.getAuthorizations(request, userRepository, workspaceRepository);
        } catch (VisalloAccessDeniedException ex) {
            if (fallbackToPublic) {
                result.authorizations = userRepository.getAuthorizations(user);
                result.requiredFallback = true;
            } else {
                throw ex;
            }
        }
        return result;
    }

    private String getWorkspaceId(HttpServletRequest request) {
        String workspaceId;
        try {
            workspaceId = VisalloBaseParameterProvider.getActiveWorkspaceId(request);
        } catch (VisalloException ex) {
            workspaceId = null;
        }
        return workspaceId;
    }

    private static class GetAuthorizationsResult {
        public Authorizations authorizations;
        public boolean requiredFallback;
    }
}
