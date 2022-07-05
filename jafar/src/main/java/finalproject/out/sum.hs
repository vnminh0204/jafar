module Main where

import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 3) regF 
    , Jump (Abs 24) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue (-1)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue 0) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regE 
    , Store regA (IndAddr regE) 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 24) regA 
    , Push regA 
    , Pop regA 
    , Pop regE 
    , Store regA (IndAddr regE) 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 73) regA 
    , Push regA 
    , Pop regA 
    , Pop regE 
    , Store regA (IndAddr regE) 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (ImmValue 2) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (DirAddr 3) regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 58) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regF 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = runWithDebugger (debuggerSimplePrint myShow) [prog0]

