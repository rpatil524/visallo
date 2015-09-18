package org.visallo.it.old;

public class VertexIntegrationTest extends VertextTestBase {
//
//    private static final String PROPERTY_QUERY_STRING = PROPERTY_VALUE_PREFIX;
//    private static final String NO_MATCHING_PROPERTY_VALUE = "NoMatchingProperty";
//    private static final String EMPTY_FILTER = "[]";
//

//
//    @Test
//    public void testSearchVisibleWithQueryString() throws VisalloClientApiException {
//        VertexVisibilityHelper helper = new VertexVisibilityHelper();
//        List<ClientApiVertex> vertices;
//
//        // matches all visible
//        vertices = helper.vertexApi.vertexSearch(PROPERTY_QUERY_STRING, EMPTY_FILTER, null, null, null, null,
//                null).getVertices();
//
//        assertVertexIds(helper.visibleVertexIds, vertices);
//
//        // matches nothing
//        vertices = helper.vertexApi.vertexSearch(NO_MATCHING_PROPERTY_VALUE, EMPTY_FILTER, null, null, null,
//                null, null).getVertices();
//
//        assertEquals(0, vertices.size());
//    }
//
//    @Test
//    public void testSearchPublicWithQueryStringForRelated() throws VisalloClientApiException {
//        RelatedVerticesHelper helper = new RelatedVerticesHelper();
//        VertexApi vertexApi = helper.vertexApi;
//        List<ClientApiVertex> vertices;
//
//        // match single
//        vertices = vertexApi.vertexSearch(PROPERTY_QUERY_STRING, EMPTY_FILTER, null, null, null, null,
//                helper.getVertexIdForSingleSearch()).getVertices();
//
//        helper.assertRelatedVerticesForSingle(vertices);
//
//        // match multiple
//        vertices = vertexApi.vertexSearch(PROPERTY_QUERY_STRING, EMPTY_FILTER, null, null, null, null,
//                helper.getVertexIdsForMultipleSearch()).getVertices();
//
//        helper.assertRelatedVerticesForMultiple(vertices);
//
//        // no match
//        vertices = vertexApi.vertexSearch(NO_MATCHING_PROPERTY_VALUE, EMPTY_FILTER, null, null, null, null,
//                helper.getVertexIdsForMultipleSearch()).getVertices();
//
//        assertEquals(0, vertices.size());
//    }
//
//    @Test
//    public void testFindRelated() throws VisalloClientApiException {
//        RelatedVerticesHelper helper = new RelatedVerticesHelper();
//        VertexApiExt vertexApi = helper.vertexApi;
//        List<ClientApiVertex> vertices;
//
//        // single
//        vertices = vertexApi.findRelated(helper.getVertexIdForSingleSearch()).getVertices();
//
//        helper.assertRelatedVerticesForSingle(vertices);
//
//        // multiple
//        vertices = vertexApi.findRelated(helper.getVertexIdsForMultipleSearch()).getVertices();
//
//        helper.assertRelatedVerticesForMultiple(vertices);
//    }
//
//    private class RelatedVerticesHelper {
//        final List<String> vertexIds;
//        final VertexApiExt vertexApi;
//
//        RelatedVerticesHelper() throws VisalloClientApiException {
//            // Vertex relationships:
//            //   0 -> 1, 2
//            //   3 -> 0, 1, 4
//            //   4 -> 5, 6
//            vertexIds = createPublicVertices(7, 1);
//            createEdge(vertexIds.get(0), vertexIds.get(1), EDGE_LABEL1);
//            createEdge(vertexIds.get(0), vertexIds.get(2), EDGE_LABEL2);
//            createEdge(vertexIds.get(3), vertexIds.get(0), EDGE_LABEL1);
//            createEdge(vertexIds.get(3), vertexIds.get(1), EDGE_LABEL1);
//            createEdge(vertexIds.get(3), vertexIds.get(4), EDGE_LABEL1);
//            createEdge(vertexIds.get(4), vertexIds.get(5), EDGE_LABEL1);
//            createEdge(vertexIds.get(4), vertexIds.get(6), EDGE_LABEL1);
//
//            vertexApi = authenticateApiUser().getVertex();
//        }
//
//        List<String> getVertexIdForSingleSearch() {
//            return ImmutableList.of(vertexIds.get(0));
//        }
//
//        List<String> getVertexIdsForMultipleSearch() {
//            return ImmutableList.of(vertexIds.get(0), vertexIds.get(3));
//        }
//
//        void assertRelatedVerticesForSingle(List<ClientApiVertex> actualVertices) {
//            assertVertexIds(
//                    ImmutableList.of(vertexIds.get(1), vertexIds.get(2), vertexIds.get(3)),
//                    actualVertices);
//        }
//
//        void assertRelatedVerticesForMultiple(List<ClientApiVertex> actualVertices) {
//            // These expected vertices are dependent on the edges set up in the constructor.
//            assertVertexIds(
//                    ImmutableList.of(
//                            vertexIds.get(0), vertexIds.get(1), vertexIds.get(2),
//                            vertexIds.get(3), vertexIds.get(4)),
//                    actualVertices);
//        }
//    }
//
//    private class VertexVisibilityHelper {
//        final List<String> allVertexIds;
//        final List<String> visibleVertexIds;
//        final VertexApi vertexApi;
//
//        VertexVisibilityHelper() throws ApiException {
//            allVertexIds = createVertices(
//                    3, ImmutableList.of("a", "b", "c"),
//                    3, ImmutableList.of("x", "y", "z"));
//            visibleVertexIds = allVertexIds.subList(0, 2); // only a and b according to user auths below
//            vertexApi = authenticateApiUser("a", "b", "x", "y").getVertex();
//        }
//    }
//
//    private VisalloApi authenticateApiUser(String... userAuths) throws VisalloClientApiException {
//        String setupWorkspaceId = setupVisalloApi.getWorkspaceId(); // capture before switching users
//        VisalloApi visalloApi = login(USERNAME_TEST_USER_2);
//        addUserAuths(visalloApi, USERNAME_TEST_USER_2, setupWorkspaceId);
//        if (userAuths.length > 0) {
//            addUserAuths(visalloApi, USERNAME_TEST_USER_2, userAuths);
//        }
//        return visalloApi;
//    }
}