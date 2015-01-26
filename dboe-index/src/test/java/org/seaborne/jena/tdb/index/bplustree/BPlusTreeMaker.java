/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  See the NOTICE file distributed with this work for additional
 *  information regarding copyright ownership.
 */

package org.seaborne.jena.tdb.index.bplustree;

import org.seaborne.jena.tdb.base.record.RecordLib ;
import org.seaborne.jena.tdb.index.Index ;
import org.seaborne.jena.tdb.index.RangeIndex ;
import org.seaborne.jena.tdb.index.RangeIndexMaker ;
import org.seaborne.jena.tdb.index.bplustree.BPlusTree ;

public class BPlusTreeMaker implements RangeIndexMaker
{
    private int order ;
    private int recordOrder ;
    private boolean trackers ;


    public BPlusTreeMaker(int order, int recordOrder, boolean trackers)
    { 
        this.order = order ; 
        this.recordOrder = recordOrder ;
        this.trackers = trackers ;
    }
    
    @Override
    public Index makeIndex() { return makeRangeIndex() ; }

    @Override
    public RangeIndex makeRangeIndex()
    {
        BPlusTree bpTree = BPlusTree.makeMem(order, recordOrder, RecordLib.TestRecordLength, 0) ;
        if ( trackers )
            bpTree = BPlusTree.addTracking(bpTree) ;
//        System.err.println("CheckingNode = "+BPlusTreeParams.CheckingNode) ;
//        System.err.println("CheckingTree = "+BPlusTreeParams.CheckingTree) ;
        return bpTree ;
    }

    @Override
    public String getLabel() { return "B+Tree order = "+order ; } 

}
