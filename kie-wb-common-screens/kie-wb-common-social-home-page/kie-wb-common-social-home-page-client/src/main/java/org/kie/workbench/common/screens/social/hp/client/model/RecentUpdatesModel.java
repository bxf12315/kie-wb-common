//package org.kie.workbench.common.screens.social.hp.client.model;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.kie.uberfire.social.activities.model.SocialActivitiesEvent;
//
//public class RecentUpdatesModel {
//
//    private Map<String, List<UpdateItem>> updateItems;
//
//    private RecentUpdatesModel() {
//        updateItems = new LinkedHashMap<String, List<UpdateItem>>();
//    }
//
//    public static RecentUpdatesModel generate( List<SocialActivitiesEvent> events ) {
//        sort( events );
//        RecentUpdatesModel recentUpdatesModel = new RecentUpdatesModel();
//
//        for ( SocialActivitiesEvent event : events ) {
//            recentUpdatesModel.add( event.getLinkTarget(), new UpdateItem( event ) );
//        }
//
//        return recentUpdatesModel;
//    }
//
//    private void add( String linkTarget,
//                      UpdateItem updateItem ) {
//        List<UpdateItem> items = updateItems.get( linkTarget );
//        if ( items == null ) {
//            items = new ArrayList<UpdateItem>();
//        }
//        items.add( updateItem );
//        updateItems.put( linkTarget, items );
//    }
//
//    private static void sort( List<SocialActivitiesEvent> events ) {
//        Collections.sort( events, reverseDateComparator() );
//    }
//
//    private static Comparator<SocialActivitiesEvent> reverseDateComparator() {
//        return new Comparator<SocialActivitiesEvent>() {
//            @Override
//            public int compare( SocialActivitiesEvent o1,
//                                SocialActivitiesEvent o2 ) {
//
//                Date date1 = o1.getTimestamp();
//                Date date2 = o2.getTimestamp();
//                if ( date1.compareTo( date2 ) == -1 ) {
//                    return 1;
//                }
//                if ( date1.compareTo( date2 ) == 1 ) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        };
//    }
//
//    private static Comparator<UpdateItem> dateComparator() {
//        return new Comparator<UpdateItem>() {
//            @Override
//            public int compare( UpdateItem o1,
//                                UpdateItem o2 ) {
//
//                Date date1 = o1.getEvent().getTimestamp();
//                Date date2 = o2.getEvent().getTimestamp();
//                return date1.compareTo( date2 );
//            }
//        };
//    }
//
//    public Map<String, List<UpdateItem>> getUpdateItems() {
//        return updateItems;
//    }
//
//    public List<UpdateItem> getUpdateItems( String key ) {
//        List<UpdateItem> items = updateItems.get( key );
//        if ( items == null ) {
//            items = new ArrayList<UpdateItem>();
//        }
//        Collections.sort( items, dateComparator() );
//        return items;
//    }
//}
